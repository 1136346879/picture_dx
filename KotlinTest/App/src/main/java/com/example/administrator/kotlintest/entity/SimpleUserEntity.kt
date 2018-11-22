package com.xfs.fsyuncai.entity


data class SimpleUserEntity(
    val id: Int?=null,
    val type: Any?=null,
    val loginAccount: String?=null,
    val memberId: String?=null,
    val mobile: String?=null,
    val safeLevel: Any?=null,
    val registerPlatform: Any?=null,
    val accountType: String?=null,
    val belongConsumerId: String?=null,
    val belongConsumerCode: String?=null,
    val companyName: Any?=null,
    val businessType: Any?=null,
    val businessLicenseNum: Any?=null,
    val msg: Any?=null,
    val isRestrict: String?=null,
    val isAccPeriod: String?=null,
    val customerName: String?=null,
    val organizationName: String?=null,
    val userName: String?=null,
    val accountContractInfo: AccountContractInfo?=null,
    val limitType: Int?=null,
    val organizationId: String?=null,
    val warehouseCode: String?=null,
    val warehouseName: String?=null,
    val customerType: String
)

data class AccountContractInfo(
    val member_id: String?=null,
    val customer_id: String?=null,
    val customer_code: String?=null,
    val all_author_single_user: String?=null,
    val contract_state: String?=null,
    val categorys: String?=null,
    val authority_list: AuthorityList?=null,
    val account_relation_list: List<AccountRelation>?=null,
    val account_releation_type_list: AccountReleationTypeList?=null,
    val relation_authority_list: RelationAuthorityList?=null,
    val need_audit_settle_relation: NeedAuditSettleRelation
)

data class RelationAuthorityList(
    val waitSettle: String?=null,
    val waitAudit: String?=null,
    val alreadyOrder: String
)

data class AccountRelation(
    val settle_account: List<SettleAccount>?=null,
    val audit_account: List<AuditAccount>?=null,
    val order_account: List<OrderAccount>
)

data class OrderAccount(
    val sub_member_id: String?=null,
    val sub_account: String
)

data class SettleAccount(
    val total_member_id: String?=null,
    val total_account: String
)

data class AuditAccount(
    val audit_member_id: String?=null,
    val audit_account: String
)

data class NeedAuditSettleRelation(
    val need_settle: List<NeedSettle>?=null,
    val need_audit: List<Any>
)

data class NeedSettle(
    val total_member_id: String?=null,
    val total_account: String
)

data class AccountReleationTypeList(
    val settle_account: List<SettleAccount>?=null,
    val audit_account: List<AuditAccount>?=null,
    val order_account: List<OrderAccount>
)

data class AuthorityList(
    val enable_audit: String?=null,
    val enable_order: String?=null,
    val enable_settle: String
)