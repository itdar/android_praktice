package com.gin.praktice.config.lang;

public class Config_Language {
    private static Config_Language instance = null;

    public String deleteSelectedSquad;
    public String deleteSelectedMemberInSelectedSquad;
    public String addMemberToSelectedSquad;
    public String modifySelectedMemberName;
    public String modifySelectedSquadName;

    public String deleteSelectedMemberFromDDay;
    public String addContactMemberToDDay;
    public String addRawMemberDirectly;
    public String addKakaoFriend;

    public String littleLateMember;
    public String superLateMember;
    public String cancelLateMember;

    public String menuModification;
    public String menuAppend;
    public String menuRemove;

    public String languageKor;
    public String languageEng;
    public String languageJpn;
    public String languageChn;
    public String languageFra;
    public String languageGer;

    //Text -> Main
    public String mainText;
    public String mainSplitorButton;

    //Text -> Splitor Main
    public String splitorMainText;
    public String wholeGroupListText;
    public String selectedMemberListText;
    public String splitorMainModifyButton;
    public String splitorMainNewSquadButton;
    public String splitorMainNewDDayButton;

    //Text -> Splitor DDay
    public String ddayText;
    public String ddayNameText;
    public String ddayNameEditText;
    public String ddayDate;
    public String ddayModifyButton;
    public String ddayAddMemberButton;
    public String ddayNextButton;

    //Alert
    public String selectMemberFirst;


    protected Config_Language() {}

    //Need to make singleton class all language configuration class
    private Config_Language(int langNumber) {
        switch (langNumber) {
            case 1: setLanguagePack(new Config_Kor()); break;
            case 2: setLanguagePack(new Config_Eng()); break;
            default: break;
        }
    }

    //DB langNumber brought
    public static Config_Language get() {
        if (instance == null)
        {
            instance = new Config_Language(2);
        }
        return instance;
    }

    public void setLanguagePack(Config_Language lang) {

        this.deleteSelectedSquad = lang.deleteSelectedSquad;
        this.deleteSelectedMemberInSelectedSquad = lang.deleteSelectedSquad;
        this.addMemberToSelectedSquad = lang.addMemberToSelectedSquad;
        this.modifySelectedMemberName = lang.modifySelectedMemberName;
        this.modifySelectedSquadName = lang.modifySelectedSquadName;

        //DDay
        this.deleteSelectedMemberFromDDay = lang.deleteSelectedMemberFromDDay;
        this.addContactMemberToDDay = lang.addContactMemberToDDay;
        this.addRawMemberDirectly = lang.addRawMemberDirectly;
        this.addKakaoFriend = lang.addKakaoFriend;

        this.littleLateMember = lang.littleLateMember;
        this.superLateMember = lang.superLateMember;
        this.cancelLateMember = lang.cancelLateMember;

        this.menuModification = lang.menuModification;
        this.menuAppend = lang.menuAppend;
        this.menuRemove = lang.menuRemove;

        this.languageKor = lang.languageKor;
        this.languageEng = lang.languageEng;
        this.languageJpn = lang.languageJpn;
        this.languageChn = lang.languageChn;
        this.languageFra = lang.languageFra;
        this.languageGer = lang.languageGer;

        //Text -> Main
        this.mainText = lang.mainText;
        this.mainSplitorButton = lang.mainSplitorButton;

        //Text -> Splitor Main
        this.splitorMainText = lang.splitorMainText;
        this.wholeGroupListText = lang.wholeGroupListText;
        this.selectedMemberListText = lang.selectedMemberListText;
        this.splitorMainModifyButton = lang.splitorMainModifyButton;
        this.splitorMainNewSquadButton = lang.splitorMainNewSquadButton;
        this.splitorMainNewDDayButton = lang.splitorMainNewDDayButton;

        //Text -> Splitor DDay
        this.ddayText = lang.ddayText;
        this.ddayNameText = lang.ddayNameText;
        this.ddayNameEditText = lang.ddayNameEditText;
        this.ddayDate = lang.ddayDate;
        this.ddayModifyButton = lang.ddayModifyButton;
        this.ddayAddMemberButton = lang.ddayAddMemberButton;
        this.ddayNextButton = lang.ddayNextButton;

        //Alert
        this.selectMemberFirst = lang.selectMemberFirst;

    }

}
