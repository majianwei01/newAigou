package com.majianwei.plat.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.majianwei.common.PageClient;
import com.majianwei.common.RedisFeignClient;
import com.majianwei.plat.domain.ProductType;
import com.majianwei.plat.mapper.ProductTypeMapper;
import com.majianwei.plat.service.IProductTypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.majianwei.util.commontools.TypeConversion;
import com.majianwei.util.constant.GlobalConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductTypeServiceImpl extends ServiceImpl<ProductTypeMapper, ProductType> implements IProductTypeService {
    @Autowired
    private ProductTypeMapper productTypeMapper;
    @Autowired
    private RedisFeignClient redisFeignClient;
    @Autowired
    private PageClient pageClient;
    //树结构菜单
    @Override
    public List<ProductType> treeStructure() {
        String proTypeValue = redisFeignClient.get(GlobalConstant.PROTYPETREEDATA);
        //如果redis中没有数据就从数据库中查出来再放到redis中
        if (StringUtils.isEmpty(proTypeValue)){
            System.out.println("这次拿的是数据库的数据");
            List<ProductType> productTypes = treeData();
            //将数据库中查到的数据存入redis缓存中
            redisFeignClient.set(GlobalConstant.PROTYPETREEDATA,JSON.toJSONString(productTypes));
            return productTypes;
        }else {
            //如果redis中有对应的数据，那么将jedis中的数据转换成对象返回
            System.out.println("这次拿的是jedis的缓存");
            return JSON.parseArray(proTypeValue, ProductType.class);
        }
    }
    /*domain准备一个子菜单的字段
     * 1.先拿到所有的产品类型
     * 2.准备一个Map，将所有的产品类型循环出来以<类型id,类型对象>形式放进来
     * 3.准备一个List集合
     * 4.将父类型（pid为0）的产品类型对象循环出来放进List集合
     * 5.如果pid不为0的话，将产品类型对象放到getChildrend的集合
     * */
    @Override
    public List<ProductType> treeData(){
        List<ProductType> allProductType = productTypeMapper.selectList(null);
        Map<Long,ProductType> map = new HashMap<>();
        for (ProductType pt : allProductType) {
            map.put(pt.getId(),pt);
        }
        //这个list是专门存放一级父类产品类型的
        List<ProductType> list = new ArrayList<>();
        for (ProductType nowProductType : allProductType) {
            if (nowProductType.getPid()==0){
                list.add(nowProductType);
            }else {
                //子类的其实就是他父类的id，通过子类id就能拿到父类对象
                Long id = nowProductType.getPid();
                ProductType parentProductType = map.get(id);
                //将子类存放到父类中
                parentProductType.getChildren().add(nowProductType);
            }
        }
        //最终返回的就是装有子类的父类
        return list;
    }



    //复写父类的删除方法
    @Override
    public boolean deleteById(Serializable id) {
        //先根据id删除当前这条数据
        boolean b = super.deleteById(id);
        //再重新查询一次数据库并将数据放到jedis缓存
        List<ProductType> productTypes = treeData();
        redisFeignClient.set(GlobalConstant.PROTYPETREEDATA, JSON.toJSONString(productTypes));
        return b;
    }
    //覆写修改方法


    @Override
    public boolean updateById(ProductType entity) {
        boolean b = super.updateById(entity);
        //再重新查询一次数据库并将数据放到jedis缓存
        List<ProductType> productTypes = treeData();
        redisFeignClient.set(GlobalConstant.PROTYPETREEDATA, JSON.toJSONString(productTypes));

        //3：页面静态化：先生成当前变的数据的页面，再生成主页面：
        Map<String,Object> productTypeMap = new HashMap<>();
        //三个参数的封装：
        //     public  static  final  String PAGE_MODEL="page_model";//页面静态需要的数据
        //    public  static  final  String TEMPLATE_FILE="template_file";//页面静态需要的模板路径
        //    public  static  final  String TARGET_FILE="target_file";//页面静态生成的静态页面路
       /* Object page_model = params.get(GlobalConstant.PAGE_MODEL);
        String  template_file = (String)params.get(GlobalConstant.TEMPLATE_FILE);
        String target_file = (String)params.get(GlobalConstant.TARGET_FILE);*/

        //redis中获取：
        productTypeMap.put(GlobalConstant.PAGE_MODEL,treeData());
        productTypeMap.put(GlobalConstant.TEMPLATE_FILE,"D:\\idea-code\\aigou_parent\\aigou_common\\aigou_common_service\\src\\main\\resources\\template\\product.type.vm");
        productTypeMap.put(GlobalConstant.TARGET_FILE,"D:\\idea-code\\aigou_parent\\aigou_common\\aigou_common_service\\src\\main\\resources\\template\\product.type.vm.html");
        pageClient.createPage(productTypeMap);


        Map<String,Object> homeMap = new HashMap<>();
        // $model.staticRoot==》对象才可以点
        Map<String,Object> modelMap = new HashMap<>();
        //D:\ideaFiles\aigou_parent\aigou_common_parent\aigou_redis_server_8848\src\main\resources
        modelMap.put("staticRoot","D:\\ideaFiles\\aigou_parent\\aigou_common_parent\\aigou_redis_server_8848\\src\\main\\resources\\");
        homeMap.put(GlobalConstant.PAGE_MODEL,modelMap);
        //D:\ideaFiles\aigou_parent\aigou_common_parent\aigou_redis_server_8848\src\main\resources\template\home.vm
        homeMap.put(GlobalConstant.TEMPLATE_FILE,"D:\\ideaFiles\\aigou_parent\\aigou_common_parent\\aigou_redis_server_8848\\src\\main\\resources\\template\\home.vm");
        homeMap.put(GlobalConstant.TARGET_FILE,"D:\\idea-code\\vue_parent\\vue_aigou\\home.html");

        pageClient.createPage(homeMap);
        return b;
    }

    //添加
    @Override
    public boolean insert(ProductType entity) {
        boolean insert = super.insert(entity);
        //再重新查询一次数据库并将数据放到jedis缓存
        List<ProductType> productTypes = treeData();
        redisFeignClient.set(GlobalConstant.PROTYPETREEDATA, JSON.toJSONString(productTypes));
        return insert;
    }
    /*面包屑：思路：获取前台的分类id：
    通过id获取到自己的老子：
    找老子的所有的儿子；
    把儿子中的自己干掉--》得到自己的兄弟姐们
            返回到前台*/
    @Override
    public List<Map<String, Object>> getCrumbs(Long productTypeId) {

        //自己
        ProductType productType = productTypeMapper.selectById(productTypeId);
        //老子的id
        Long pid = productType.getPid();
        String path = productType.getPath();//.1.2.6.
        String substring = path.substring(1, path.length() - 1);//1.2.6
        String[] split = substring.split("\\.");
        long[] longs = new long[split.length];
        List<Map<String,Object>> list = new ArrayList<>();
        for (int i = 0;i<split.length;i++){
            longs[i] = Long.parseLong(split[i]);
        }
        for (long aLong : longs) {
            //当前类型
            ProductType currentType = productTypeMapper.selectById(aLong);
            //当前类型的父id
            Long fatherTypeId = currentType.getPid();
            //找老子的儿子，有了老子的id之后就可以查出老子的儿子，直接查所有pid等于老子id的type就是老子的儿子
            Wrapper<ProductType> wrapper = new EntityWrapper<>();
            wrapper.eq("pid", fatherTypeId);
            List<ProductType> children = productTypeMapper.selectList(wrapper);
            //将儿子都遍历出来并且将当前type移除
            for (ProductType child : children) {
                if (child.getPid() == currentType.getPid()){
                    children.remove(child);
                    break;
                }
            }
            Map<String,Object> map = new HashMap<>();
            map.put("ownerProductType", currentType);
            map.put("otherProductTypes", children);
            list.add(map);
        }
        return list;
    }
}
