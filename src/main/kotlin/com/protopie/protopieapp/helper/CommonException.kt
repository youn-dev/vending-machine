package com.protopie.protopieapp.helper

enum class CommonException(
    override val errorCode: String,
    override val message: String,
) : CommonError {
    ITEM_QTY_MUST_BE_GRATER_THAN_ONE("1001", "수량은 1보다 작을 수 없습니다."),
    ITEM_NOT_FOUND("1002", "해당 아이템이 존재하지 않습니다. item : {0}"),
    QTY_MUST_BE_SAME_OR_GRATER_THAN_BUYING_QTY("1003", "재고는 구매 수량보다 많거나 같아야 합니다."),
    CHANGE_QTY_MUST_BE_GRATER_THAN_ZERO("1004", "거스름돈 수량은 0보다 작을 수 없습니다."),
    CASH_MUST_BE_GRATER_THAN_ZERO("1005", "현금은 0원 보다 작을 수 없습니다."),
    CASH_NOT_FOUND("1006", "해당 화폐는 존재하지 않습니다."),
    CASH_QTY_MUST_BE_GRATER_THAN_REQUESTED_QTY("1007", "화폐 수량은 요청 수량보다 커야 합니다."),
    CASH_INPUT_MUST_BE_GRATER_THAN_ITEM_PRICE("1008", "화폐 투입량 상품 가격보다 커야합니다."),
    ;
}
