package com.xfs.fsyuncai.entity

/**
 * Created by kangf on 2018/7/16.
 */
class AccountEntity {


    /**
     * shopCustomerAddress : {"detail_address":"海鑫北路9号","province_code":"1","status":"2","city_id":100,"district_id":646,"member_id":0,"add_alias":"测试","province_id":1,"create_time":"2018-11-03T08:54:44.000+0000","city_code":"2810","ship_add_id":1721,"customer_id":"CN00001901","city_name":"大兴区","street_id":0,"address_type":"10","linkman":null,"email":"","warehouse_code":"1","province_name":"北京","trade_time":"2018-11-05T05:48:20.000+0000","area_name":"大兴郊区","street_name":null,"receiverList":[{"mobile":"18131813320","status":10,"id":4130,"address_id":1721,"create_time":"2018-11-05T05:40:11.000+0000","receiver_name":"李燕","receiver_id":0}],"update_time":"2018-11-05T05:48:20.000+0000","street_code":null,"district_code":"4205","office_phone":"","is_default":"1"}
     * accountContractInfo : {"errorMessage":"查询成功！","errorCode":0,"resultList":{"member_id":"10260","authority_list":{"enable_audit":20,"enable_order":10,"enable_settle":20},"categorys":"88,96,96,95","account_relation_list":[{"settle_account":[{"total_member_id":10258,"total_account":"devsettle123"}],"audit_account":[{"audit_member_id":10261,"audit_account":"devapproval123"}],"order_account":[{"sub_member_id":"10260","sub_account":"devorder123"}]}],"account_releation_type_list":{"settle_account":[{"total_member_id":10258,"total_account":"devsettle123"}],"audit_account":[{"audit_member_id":10261,"audit_account":"devapproval123"}],"order_account":[{"sub_member_id":10260,"sub_account":"devorder123"}]},"relation_authority_list":{"waitSettle":20,"waitAudit":20,"alreadyOrder":10},"need_audit_settle_relation":{"need_settle":[{"total_member_id":10258,"total_account":"devsettle123"}],"need_audit":[{"audit_member_id":10261,"audit_account":"devapproval123"}]},"customer_id":"CN00000388","customer_code":"01.02.1514","all_author_single_user":20,"contract_state":"1"}}
     * errorMessage : 会员登录成功！
     * member : {"member_id":10260,"account_type":2,"belong_consumer_code":"01.02.1514","safe_level":2,"mobile_phone":null,"logo":null,"belong_consumerId":"CN00000388","customer_name":"0106758金坛建工集团有限公司(李东)","login_account":"devorder123","customer_id":"CN00000388","customer_code":"01.02.1514"}
     * errorCode : 1
     * token : eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqd3QiLCJpYXQiOjE1MzE3MTk5MjYsInN1YiI6IntcImxvZ2luQWNjb3VudFwiOlwiZGV2b3JkZXIxMjNcIixcImxvZ2luRmxhZ1wiOnRydWUsXCJtZW1iZXJJZFwiOlwiMTAyNjBcIn0iLCJleHAiOjE1MzE3MjM1MjZ9.sIiyKbpM_0nhsZZW-oC07c1K6-WWYD05ClIWA7iXyLs
     */
    var shopCustomerAddress: ShopCustomerAddressBean? = null
    var accountContractInfo: AccountContractInfoBean? = null
    var errorMessage: String? = null
    var member: MemberBean? = null
    var errorCode: Int = 0
    var token: String? = null

    class ShopCustomerAddressBean {
        /**
         * detail_address : 海鑫北路9号
         * province_code : 1
         * status : 2
         * city_id : 100
         * district_id : 646
         * member_id : 0
         * add_alias : 测试
         * province_id : 1
         * create_time : 2018-11-03T08:54:44.000+0000
         * city_code : 2810
         * ship_add_id : 1721
         * customer_id : CN00001901
         * city_name : 大兴区
         * street_id : 0
         * address_type : 10
         * linkman : null
         * email :
         * warehouse_code : 1
         * province_name : 北京
         * trade_time : 2018-11-05T05:48:20.000+0000
         * area_name : 大兴郊区
         * street_name : null
         * receiverList : [{"mobile":"18131813320","status":10,"id":4130,"address_id":1721,"create_time":"2018-11-05T05:40:11.000+0000","receiver_name":"李燕","receiver_id":0}]
         * update_time : 2018-11-05T05:48:20.000+0000
         * street_code : null
         * district_code : 4205
         * office_phone :
         * is_default : 1
         */

        var detail_address: String? = null
        var province_code: String? = null
        var status: String? = null
        var city_id: Int = 0
        var district_id: Int = 0
        var member_id: Int = 0
        var add_alias: String? = null
        var province_id: Int = 0
        var create_time: String? = null
        var city_code: String? = null
        var ship_add_id: Int = 0
        var customer_id: String? = null
        var city_name: String? = null
        var street_id: Int = 0
        var address_type: String? = null
        var linkman: Any? = null
        var email: String? = null
        var warehouse_code: String? = null
        var province_name: String? = null
        var trade_time: String? = null
        var area_name: String? = null
        var street_name: Any? = null
        var update_time: String? = null
        var street_code: Any? = null
        var district_code: String? = null
        var office_phone: String? = null
        var is_default: String? = null
        var receiverList: List<ReceiverListBean>? = null

        class ReceiverListBean {
            /**
             * mobile : 18131813320
             * status : 10
             * id : 4130
             * address_id : 1721
             * create_time : 2018-11-05T05:40:11.000+0000
             * receiver_name : 李燕
             * receiver_id : 0
             */

            var mobile: String? = null
            var status: Int = 0
            var id: Int = 0
            var address_id: Int = 0
            var create_time: String? = null
            var receiver_name: String? = null
            var receiver_id: Int = 0
        }
    }

    class AccountContractInfoBean {
        /**
         * errorMessage : 查询成功！
         * errorCode : 0
         * resultList : {"member_id":"10260","authority_list":{"enable_audit":20,"enable_order":10,"enable_settle":20},"categorys":"88,96,96,95","account_relation_list":[{"settle_account":[{"total_member_id":10258,"total_account":"devsettle123"}],"audit_account":[{"audit_member_id":10261,"audit_account":"devapproval123"}],"order_account":[{"sub_member_id":"10260","sub_account":"devorder123"}]}],"account_releation_type_list":{"settle_account":[{"total_member_id":10258,"total_account":"devsettle123"}],"audit_account":[{"audit_member_id":10261,"audit_account":"devapproval123"}],"order_account":[{"sub_member_id":10260,"sub_account":"devorder123"}]},"relation_authority_list":{"waitSettle":20,"waitAudit":20,"alreadyOrder":10},"need_audit_settle_relation":{"need_settle":[{"total_member_id":10258,"total_account":"devsettle123"}],"need_audit":[{"audit_member_id":10261,"audit_account":"devapproval123"}]},"customer_id":"CN00000388","customer_code":"01.02.1514","all_author_single_user":20,"contract_state":"1"}
         */

        var errorMessage: String? = null
        var errorCode: Int = 0
        var resultList: ResultListBean? = null


        class ResultListBean {
            /**
             * member_id : 10260
             * authority_list : {"enable_audit":20,"enable_order":10,"enable_settle":20}
             * categorys : 88,96,96,95
             * account_relation_list : [{"settle_account":[{"total_member_id":10258,"total_account":"devsettle123"}],"audit_account":[{"audit_member_id":10261,"audit_account":"devapproval123"}],"order_account":[{"sub_member_id":"10260","sub_account":"devorder123"}]}]
             * account_releation_type_list : {"settle_account":[{"total_member_id":10258,"total_account":"devsettle123"}],"audit_account":[{"audit_member_id":10261,"audit_account":"devapproval123"}],"order_account":[{"sub_member_id":10260,"sub_account":"devorder123"}]}
             * relation_authority_list : {"waitSettle":20,"waitAudit":20,"alreadyOrder":10}
             * need_audit_settle_relation : {"need_settle":[{"total_member_id":10258,"total_account":"devsettle123"}],"need_audit":[{"audit_member_id":10261,"audit_account":"devapproval123"}]}
             * customer_id : CN00000388
             * customer_code : 01.02.1514
             * all_author_single_user : 20
             * contract_state : 1
             */

            var member_id: String? = null

            var authority_list: AuthorityListBean? = null
            var categorys: String? = null
            var account_releation_type_list: AccountReleationTypeListBean? = null
            var relation_authority_list: RelationAuthorityListBean? = null
            var need_audit_settle_relation: NeedAuditSettleRelationBean? = null
            var customer_id: String? = null
            var customer_code: String? = null
            var all_author_single_user: Int = 0
            var contract_state: String? = null
            var account_relation_list: List<AccountRelationListBean>? = null

            class AuthorityListBean {
                /**
                 * enable_audit : 20
                 * enable_order : 10
                 * enable_settle : 20
                 */

                var enable_audit: Int = 0
                var enable_order: Int = 0
                var enable_settle: Int = 0
            }

            class AccountReleationTypeListBean {
                var settle_account: List<SettleAccountBean>? = null
                var audit_account: List<AuditAccountBean>? = null
                var order_account: List<OrderAccountBean>? = null

                class SettleAccountBean {
                    /**
                     * total_member_id : 10258
                     * total_account : devsettle123
                     */

                    var total_member_id: Int = 0
                    var total_account: String? = null
                }

                class AuditAccountBean {
                    /**
                     * audit_member_id : 10261
                     * audit_account : devapproval123
                     */

                    var audit_member_id: Int = 0
                    var audit_account: String? = null
                }

                class OrderAccountBean {
                    /**
                     * sub_member_id : 10260
                     * sub_account : devorder123
                     */

                    var sub_member_id: Int = 0
                    var sub_account: String? = null
                }
            }

            class RelationAuthorityListBean {
                /**
                 * waitSettle : 20
                 * waitAudit : 20
                 * alreadyOrder : 10
                 */

                var waitSettle: Int = 0
                var waitAudit: Int = 0
                var alreadyOrder: Int = 0
            }

            class NeedAuditSettleRelationBean {
                var need_settle: List<NeedSettleBean>? = null
                var need_audit: List<NeedAuditBean>? = null

                class NeedSettleBean {
                    /**
                     * total_member_id : 10258
                     * total_account : devsettle123
                     */

                    var total_member_id: Int = 0
                    var total_account: String? = null
                }

                class NeedAuditBean {
                    /**
                     * audit_member_id : 10261
                     * audit_account : devapproval123
                     */

                    var audit_member_id: Int = 0
                    var audit_account: String? = null
                }
            }

            class AccountRelationListBean {
                var settle_account: List<SettleAccountBeanX>? = null
                var audit_account: List<AuditAccountBeanX>? = null
                var order_account: List<OrderAccountBeanX>? = null

                class SettleAccountBeanX {
                    /**
                     * total_member_id : 10258
                     * total_account : devsettle123
                     */

                    var total_member_id: Int = 0
                    var total_account: String? = null
                }

                class AuditAccountBeanX {
                    /**
                     * audit_member_id : 10261
                     * audit_account : devapproval123
                     */

                    var audit_member_id: Int = 0
                    var audit_account: String? = null
                }

                class OrderAccountBeanX {
                    /**
                     * sub_member_id : 10260
                     * sub_account : devorder123
                     */

                    var sub_member_id: String? = null
                    var sub_account: String? = null
                }
            }
        }
    }


    class MemberBean {
        /**
         * member_id : 10260
         * account_type : 2
         * belong_consumer_code : 01.02.1514
         * safe_level : 2
         * mobile_phone : null
         * logo : null
         * belong_consumerId : CN00000388
         * customer_name : 0106758金坛建工集团有限公司(李东)
         * login_account : devorder123
         * customer_id : CN00000388
         * customer_code : 01.02.1514
         */

        var member_id: Int? = 0
        var account_type: Int? = 0
        var belong_consumer_code: String? = null
        var safe_level: Int? = 0
        var mobile_phone: String? = null
        var logo: String? = null
        var belong_consumerId: String? = null
        var customer_name: String? = null
        var login_account: String? = null
        var customer_id: String? = null
        var customer_code: String? = null
    }
}
