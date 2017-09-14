package com.lovetoothalliance.app.constant;

/**
 * Author:lt
 * time:2017/8/1.
 * contact：weileng143@163.com
 *
 * @description
 */

public class ServiceUrl {
    //种牙:b9f7ce10-eb24-46ba-a5bd-a7c99fe3b377是id
    //镶牙:856dc4d0-08bd-4cd9-b85d-3f391f203554


    /**
     * 服务器基础地址
     */
    public static String SERVER_ADDRESS = "http://aclmmanage.jshec.cn:8856/APIInterface.ashx" +
            "?AppKey=2c07694b782c43859defb6b9bbed4c7d&md5Key=78403D5AE398E69CA83A19A743240A9687EBAE78" +
            "&method="; // 测试线

//    /**
//     * 图片测试地址
//     */
//    public static String SERVER_ADDRESS_IMG="http://white.viphk.ngrok.org/Apiinterface.ashx"+
//            "?AppKey=2c07694b782c43859defb6b9bbed4c7d&md5Key=78403D5AE398E69CA83A19A743240A9687EBAE78" +
//            "&method=";

    /**
     * 首页获取附近的店
     */
    public static String Get_Nearby_Store;
    /**
     * 首页的种牙传值传入guid (种牙和镶牙)
     *
     */
    public static String Get_ToGuid_Product;
    /**
     * 店铺详情的接口
     */
    public static String Get_Store_Detail;
    /**
     * 搜索店铺里面的产品
     */
    public static String Get_Search_Product;  //暂时没有数据
    /**
     * 登录
     */
    public static String Login_Tooth;
    /**
     * 注册
     */
    public static String Register_Tooth;
    /**
     * 获取店铺评论接口
     */
    public static String Get_Store_Comment;
    /**
     * 评论接口  暂时没有数据(已测试)
     */
    public static String Get_Comment;

    /**
     * 提交订单和获取all订单接口
     */
    public static String Get_Order;

    /**
     * 获取专家接口
     */
    public static String Get_Specialist;

    /**
     * 确定支付接口
     */
    public static String Get_Ok_Pay;

    /**
     * 积分商城接口
     */
    public static String Get_Integral_Shop;

    /**
     * 健康知识
     */
    public static String Get_Health_Knowledge;

    /**
     * 诊断记录
     */
    public static String Get_Diagnostic_Record;

    /**
     * 诊断记录待评价的评价接口
     */
    public static String Get_Evaluate_Commit;
    /**
     * 诊断记录取消订单接口
     */
    public static String Cancle_Order;

    /**
     * 上传多张图片接口
     */
    public static String Upload_More_Pic;

    /**
     * 收货地址
     */
    public static String Get_Commdity_Address;

    /**
     * 新增收货地址
     */
    public static String Get_Add_NewAddress;
    /**
     * 修改收货的默认地址
     */
    public static String Update_Default_Address;
    /**
     * 删除收货地址
     */
    public static String Delete_Address;
    /**
     * 编辑收货地址
     */
    public static String Compile_Commodity_Address;

    /**
     * 获取个人信息接口
     */
    public static String Get_PersonInformation;

    /**
     * 个人信息提交接口
     */
    public static String Commit_PersonInfo;

    /**
     * 我的团队
     */
    public static String Get_My_Team;

    /**
     * 待选择加一个确定收货按钮来测试评价
     */
    public static String Commit_Ok;

    /**
     * 搜索接口
     */
    public static String Search_All_Data;

    /**
     * 法律顾问
     */
    public static  String Counsel_Counselor;


     //登录
//    http://aclmmanage.jshec.cn:8856/APIInterface.ashx?AppKey=2c07694b782c43859defb6b9bbed4c7d&md5Key=
//    // 78403D5AE398E69CA83A19A743240A9687EBAE78&method=DistributorLogin&MemLoginID=登陆ID&Pwd=md5Password&password=md5Password

    //注册
//    http://aclmmanage.jshec.cn:8856/APIInterface.ashx?AppKey=2c07694b782c43859defb6b9bbed4c7d&md5Key=
//    // 78403D5AE398E69CA83A19A743240A9687EBAE78&method=
//    // MemberRegistration&MemberName= phone&Mobile=phone&Pwd=md5password&password=md5password&CommendPeople=推存人登陆Id

//    //    //登录
//    public static String url4="http://aclmmanage.jshec.cn:8856/APIInterface.ashx?AppKey=2c07694b782c43859d" +
//            "efb6b9bbed4c7d&md5Key=78403D5AE398E69CA83A19A743240A9687EBAE78&method=MemberRegistration&MemberName=" +
//            " 13725150568&Mobile=13725150568&Pwd="+ EncodeUtil.encode_MD5("123456")+"&password" +
//            "="+EncodeUtil.encode_MD5("123456")+"&CommendPeople=13725150568";
//
//    //获取店铺评论接口
//    public static String url5="http://aclmmanage.jshec.cn:8856/APIInterface.ashx?AppKey=2c07694b782" +
//            "c43859defb6b9bbed4c7d&md5Key=78403D5AE398E69CA83A19A7" +
//            "43240A9687EBAE78&method=Get&pageNo=1&pageSize=20&ArticleGuid=3cb5ec99-1b07-4065-903e-4b86b4abde96";
//
//    //评论接口
//    public static String url6="http://aclmmanage.jshec.cn:8856/APIInterface.ashx?" +
//            "AppKey=2c07694b782c43859defb6b9bbed4c7d&md5Key=78403D5AE398E69CA83A19A743240A9687EBAE78" +
//            "&method=SearchArticleComment&pageNo=1&pageSize=20&ArticleGuid=3cb5ec99-1b07-4065-903e-4b86b4abde96";

    //种牙
    //http://aclmmanage.jshec.cn:8856/APIInterface.ashx?AppKey=2c07694b782c43859defb6b9bbe
    // d4c7d&md5Key=78403D5AE398E69CA83A19A743240A9687EBAE78&method=GetProductById&id=

    //获取产品是否是抢购产品，如果是返回抢购实体，不是返回空数组
   // http://aclmmanage.jshec.cn:8856/APIInterface.ashx?AppKey=2c07694b782c43859defb6b9bbed4c7d
    // &md5Key=78403D5AE398E69CA83A19A743240A9687EBAE78&method=GetQiangGouProductByGuid&guid=productid

    //其他项目和专家详情页面
//    http://aclmmanage.jshec.cn:8856/APIInterface.ashx?AppKey=2c07694b782c43859defb6b9bbed4c7d&md5Key=
//    // 78403D5AE398E69CA83A19A743240A9687EBAE78&method=GetSpecilistList if(shopName){
//    url=httpurl+'method='+method+'&shopName='+shopName;
//      }else{
//        var str=x?('&x='+x+'&y='+y):'';
//        url=httpurl+'method='+method+'&city='+c+str;
//        }


    //提交接口有两个有一个是立即购买的，一个是购买购物车里的
//    http://aclmmanage.jshec.cn:8856/APIInterface.ashx?AppKey=2c07694b782c43859defb6b9bbed4c7d&md5Key=7
// 8403D5AE398E69CA83A19A743240A9687EBAE78&method =BuyRightNow&productGuid='+buyNowGoodsDetail.guid+'&detail
// ='+buyNowGoodsDetail.detail+'&buyNumber='+buyNowGoodsDetail.BuyNumber+'&memLoginId='+buyNowGoodsDetail.
// memLoginId+'&dispatchPrice='+expinfo.DispatchPrice+
    // '&addressGuid='+addressinfo.AddressGuid+'&dispatchName='+expinfo.DispatchName+'&DispatchModeGuid='+expinfo.DispatchModeGuid

    // http://aclmmanage.jshec.cn:8856/APIInterface.ashx?AppKey=2c07694b782c43859defb6b9bbed4c7d&md5Key=7
    // 8403D5AE398E69CA83A19A743240A9687EBAE78&method
    // =CreateOrderV2&SubmitMethod=Post&parms=' + decodeURIComponent(decodeURIComponent( JSON.stringify(orderinfo)))

    //支付后提交到后台
//    http://aclmmanage.jshec.cn:8856/APIInterface.ashx?AppKey=2c07694b782c43859defb6b9bbed4c7d&md5Key=
    // 78403D5AE398E69CA83A19A743240A9687EBAE78&method=WxPrePay&orderNumber=&m=MemLoginId

    //诊断记录
//    http://aclmmanage.jshec.cn:8856/APIInterface.ashx?AppKey=2c07694b782c43859defb6b9bbed4c7d&md
    // 5Key=78403D5AE398E69CA83A19A743240A9687EBAE78&method
    // =SearchOrderInfo&&f=Guid,isReturn,OrderNumber,DispatchPrice,IsBack,
    // PaymentStatus,OderStatus,DispatchModeName,ShipmentNumber,PaymentStatus,
    // ShipmentStatus,AlreadPayPrice,ShouldPayPrice,CreateTime,ProcessState,DispatchModeName,ShipmentNumber,OderStatus,PaymentStatus,
    // ShipmentStatus&goodsFields=Name,BuyPrice,BuyNumber,Attributes,OriginalImge,ProductGuid,IsComment

    static {
        mySetUrl();// 设置地址
    }

    public static void mySetUrl() {
        //获取附近的店
        Get_Nearby_Store=SERVER_ADDRESS+"GetNearShop&x=2.66&y=42.99";
        //种牙
        Get_ToGuid_Product=SERVER_ADDRESS+"GetProductById&id=";
        //店铺详情的接口
        Get_Store_Detail=SERVER_ADDRESS+"GetShopByGuid&guid=3cb5ec99-1b07-4065-903e-4b86b4abde96";
        //搜索店铺里面的产品
        Get_Search_Product=SERVER_ADDRESS+"SearchProductV2&pageNo=1&pageSize=20&AgentID=1372515056";
        //登录
        Login_Tooth=SERVER_ADDRESS+"DistributorLogin&MemLoginID=";
        //注册
        Register_Tooth=SERVER_ADDRESS+"MemberRegistration&MemberName=";
        //获取店铺评论接口
        Get_Store_Comment=SERVER_ADDRESS+"Get&pageNo=1&pageSize=20&ArticleGuid=3cb5ec99-1b07-4065-903e-4b86b4abde96";
        //评论接口
        Get_Comment=SERVER_ADDRESS+"SearchArticleComment&pageNo=1&pageSize=20&ArticleGuid=3cb5ec99-1b07-4065-903e-4b86b4abde96";
        //获取提交的订单和所有的订单  //productGuid产品Id //detail规格代码 //buyNumber购买数量
        //memLoginId会员Id  //dispatchPrice快递价格  //AddressGuid地址Id  //dispatchName快递名称 //DispatchModeGuid快递方式guid
        Get_Order=SERVER_ADDRESS+"BuyRightNow&productGuid=";

        //获取专家
        Get_Specialist=SERVER_ADDRESS+"GetSpecilistList";
        //确定支付
        Get_Ok_Pay=SERVER_ADDRESS+"WxPrePay&orderNumber=";
        //积分商城
        Get_Integral_Shop=SERVER_ADDRESS+"SearchScoreProduct&pageNo=1&pageSize=1&f=*";
        //健康知识  ，只传guid和CategoryName
        Get_Health_Knowledge=SERVER_ADDRESS+"SearchArticle&f=*";
        //诊断记录  paymentStatus支付状态  0未支付 2已支付 oderStatus=5已完成 shipmentStatus 3备货中 1已发货 2已收货  0待发货
        Get_Diagnostic_Record=SERVER_ADDRESS+"SearchOrderInfo&&f=Guid,isReturn,OrderNumber,DispatchPrice,IsBack," +
                "PaymentStatus,OderStatus,DispatchModeName,ShipmentNumber,PaymentStatus,ShipmentStatus,AlreadPayPrice,ShouldPayPrice," +
                "CreateTime,ProcessState,DispatchModeName,ShipmentNumber,OderStatus," +
                "PaymentStatus,ShipmentStatus&goodsFields=Name,BuyPrice,BuyNumber,Attributes,OriginalImge,ProductGuid,IsComment";
        //诊断记录取消订单 CancelOrder&MemLoginID=' + MemLoginID + '&orderNumber=' + OrderNumber
        Cancle_Order=SERVER_ADDRESS+"CancelOrder&";
        //诊断记录的待评价的评价
        /**
         * level='+conformStar+'&level2='+mannerStar+'&leve3=
         * '+speedStar+'&attributes='+attributes +'&img='+imgStr+'&isAnonymous='+isH+'&content='+ strContext +
         * '&Guid=' + proGuid +'&u=' +MemLoginID+ '&orderNumber=' + orderNumber  +'&name=' +name
         */
        Get_Evaluate_Commit=SERVER_ADDRESS+"InsertCommentV2&";
        //上传多张图片 http://aclmmanage.jshec.cn:8856/APIInterface.ashx?AppKey=2c07694b782c43859defb6b9bbed4c7d&m
        // d5Key=78403D5AE398E69CA83A19A743240A9687EBAE78&method=UploadImage
        Upload_More_Pic=SERVER_ADDRESS+"UploadImage";
        //收货地址
        Get_Commdity_Address=SERVER_ADDRESS+"SearchAddress&memberName=";
        //新增收货的地址 =InsertAddress&memberName='+MemLoginID+'&name='+temp.Name+'&mobile=" +
//        "'+temp.Mobile+'&address='+temp.address_1+' '+temp.address_2+' '+temp.address_3+' '+temp.address_4";
        Get_Add_NewAddress=SERVER_ADDRESS+"InsertAddress&memberName=";
        //修改收货的默认地址  method=UpdateAddress&Guid=guid&default=1&memberName=memLoginId
        Update_Default_Address=SERVER_ADDRESS+"UpdateAddress&Guid=";
        //删除收货地址  method=DeleteAddress&Id=guid
        Delete_Address=SERVER_ADDRESS+"DeleteAddress&guid=";
        //编辑收货地址 UpdateAddress&Guid=guid&default=1&memberName=memLoginId&address=&name=&email=
        Compile_Commodity_Address=SERVER_ADDRESS+"UpdateAddress&Guid=";
        //个人信息接口 method=GetMemberById&MemLoginID=13725150568&f=*
        Get_PersonInformation=SERVER_ADDRESS+"GetMemberById&MemLoginID=";
        //提交个人信息  UpdateMember&memLoginId=13725150568&字段1=值1&字段1=值1&......
        Commit_PersonInfo=SERVER_ADDRESS+"UpdateMember&memberName=";
        //我的团队  &method=GetTeamList&MemLoginID=13725150568
        Get_My_Team=SERVER_ADDRESS+"GetTeamList";
        //确定（待使用） http://aclmmanage.jshec.cn:8856/APIInterface.ashx?App
        // Key=2c07694b782c43859defb6b9bbed4c7d&md5Key=78403D5AE398E69CA83A19A74
        // 3240A9687EBAE78&method=CinfirmReceipt&m=' + memberLoginID + '&orderNumber=' + OrderNumber
        Commit_Ok=SERVER_ADDRESS+"CinfirmReceipt&m=";
        //搜索接口 http://aclmmanage.jshec.cn:8856/APIInterface.ashx?AppKey=2c07694b782c43859defb6b9b
        // bed4c7d&md5Key=78403D5AE398E69CA83A19A743240A9687EBAE78&method=SearchProductV2&keyword=kw&f=* (默认分页)
        Search_All_Data=SERVER_ADDRESS+"SearchProductV2&keyword=kw&f=*";
        //法律顾问
        Counsel_Counselor=SERVER_ADDRESS+"SearchArticle&Title=法律顾问";
    }
}
