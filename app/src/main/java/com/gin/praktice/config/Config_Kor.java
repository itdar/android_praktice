package com.gin.praktice.config;

public enum Config_Kor implements Config_Language {
    CONFIG;

    public static String deleteSelectedSquad = "선택모임 삭제";
    public static String deleteSelectedMemberInSelectedSquad = "선택모임 선택멤버 삭제";
    public static String addMemberToSelectedSquad = "선택모임에 새 멤버 추가";
    public static String modifySelectedMemberName = "선택멤버 이름 수정";
    public static String modifySelectedSquadName = "선택모임 이름 수정";

    public static String deleteSelectedMemberFromDDay = "모임날에서 선택멤버 제거";
    public static String addContactMemberToDDay = "모임날 멤버 주소록에서 추가";
    public static String addRawMemberDirectly = "모임날 멤버 직접입력 추가";

    public static String littleLateMember = "선택멤버 조금 늦게옴";
    public static String supperLateMember = "선택멤버 많이 늦게옴";
    public static String cancelLateMember = "선택멤버 늦은 것 취소";

    public static String languageKor = "한국어";
    public static String languageEng = "English";
    public static String languageJpn = "Japanese";
    public static String languageChn = "Chinese";
    public static String languageFrn = "French";
    public static String languageGer = "German";

}
