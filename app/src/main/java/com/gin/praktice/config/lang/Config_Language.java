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

    //Toast
    // -> Main
    public String toastSelectSquadFirst;
    public String toastSelectMemberFirst;
    public String toastAlreadyExistName;
    // -> DDay
    public String toastDDaySelectMemberFirst;
    public String toastDDayAddAllMemberFirst;
    public String toastDDayFillTitleOrDateFirst;
    public String toastDDayAlreadyExistName;
    // -> Location
    public String toastLocationSelectMemberFirst;
    public String toastLocationSelectHostMemberFirst;
    public String toastLocationFillLocationNameMoney;
    public String toastLocationAddAllLocationMemberFirst;
    // -> Result
    public String toastResultDetailCopy;
    public String toastResultMoneyToSendCopy;

    //Text -> Main
    public String mainText;
    public String mainSplitorButton;

    //Message
    public String msgDeleteConfirm;

    //Text -> Splitor Main
    public String splitorMainText;
    public String wholeGroupListText;
    public String selectedMemberListText;
    public String splitorMainModifyButton;
    public String splitorMainNewSquadButton;
    public String splitorMainNewDDayButton;
    public String titleMotification;
    public String titleDeleteConfirm;

    //Text -> Splitor DDay
    public String ddayText;
    public String ddayNameText;
    public String ddayNameEditText;
    public String ddayDate;
    public String ddayModifyButton;
    public String ddayAddMemberButton;
    public String ddayNextButton;
    public String ddayTitleAddDDayMember;
    public String ddayTitleDDayModification;
    public String ddayContactName;
    public String ddayContactNumber;
    public String ddayReceivedName;
    public String ddayReceivedBank;
    public String ddayReceiveAccount;
    public String titleLocationMemberOption;

    //Text -> Splitor Location
    public String locationVisitedText;
    public String locationStoreName;
    public String locationNameEditText;
    public String locationTotalMoney;
    public String locationMoneyEditText;
    public String locationMemberDeleteButton;
    public String locationAddMemberButton;
    public String locationDetailOptionButton;
    public String locationSetManagerButton;
    public String addMoreLocationButton;
    public String locationNextButton;

    //Text -> Splitor Result
    public String resultOfCalc;
    public String resultTextView1;
    public String resultTextView2;
    public String copyResultButton1;
    public String copyResultButton2;
    public String resultSaveButton;
    public String resultDoneButton;

    //Text -> Splitor Result outputWriting
    public String resultOfThisMember;
    public String resultTo;
    public String resultToBeSent;

    public String resultDDayName;
    public String resultDate;
    public String resultLocationName;
    public String resultLocationMoney;
    public String resultHost;
    public String resultTotalMember;

    //Text -> Splitor Add new squad (ans)
    public String ansAddNewSquadText;
    public String ansSquadNameText;
    public String ansSquadMemberListText;
    public String ansSquadNameEditText;
    public String ansContactAddButton;
    public String ansAddMemberButton;
    public String ansDeleteButton;
    public String ansNewSquadButton;

    //Text -> Splitor Add member directly (amd)
    public String amdAddMemberText;
    public String amdNameText;
    public String amdBankNameText;
    public String amdBankAccountText;
    public String amdRemarkText;
    public String amdNameEditText;
    public String amdBankNameEditText;
    public String amdBankAccountEditText;
    public String amdRemarkEditText;
    public String amdCancelButton;
    public String amdAddButton;

    //Text -> Splitor Location member add (lma)
    public String lmaAddUsingDDayMemText;
    public String lmaCancelButton;
    public String lmaAddButton;

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
            instance = new Config_Language(1);
        }
        return instance;
    }

    public void setLanguagePack(Config_Language lang) {

        this.deleteSelectedSquad = lang.deleteSelectedSquad;
        this.deleteSelectedMemberInSelectedSquad = lang.deleteSelectedMemberInSelectedSquad;
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

        //Toast
        // -> Main
        this.toastSelectSquadFirst = lang.toastSelectSquadFirst;
        this.toastSelectMemberFirst = lang.toastSelectMemberFirst;
        this.toastAlreadyExistName = lang.toastAlreadyExistName;
        // -> DDay
        this.toastDDaySelectMemberFirst = lang.toastDDaySelectMemberFirst;
        this.toastDDayAddAllMemberFirst = lang.toastDDayAddAllMemberFirst;
        this.toastDDayFillTitleOrDateFirst = lang.toastDDayFillTitleOrDateFirst;
        this.toastDDayAlreadyExistName = lang.toastDDayAlreadyExistName;
        // -> Location
        this.toastLocationSelectMemberFirst = lang.toastLocationSelectMemberFirst;
        this.toastLocationSelectHostMemberFirst = lang.toastLocationSelectHostMemberFirst;
        this.toastLocationFillLocationNameMoney = lang.toastLocationFillLocationNameMoney;
        this.toastLocationAddAllLocationMemberFirst = lang.toastLocationAddAllLocationMemberFirst;
        // -> Result
        this.toastResultDetailCopy = lang.toastResultDetailCopy;
        this.toastResultMoneyToSendCopy = lang.toastResultMoneyToSendCopy;

        //Message
        this.msgDeleteConfirm = lang.msgDeleteConfirm;

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
        this.titleMotification = lang.titleMotification;
        this.titleDeleteConfirm = lang.titleDeleteConfirm;

        //Text -> Splitor DDay
        this.ddayText = lang.ddayText;
        this.ddayNameText = lang.ddayNameText;
        this.ddayNameEditText = lang.ddayNameEditText;
        this.ddayDate = lang.ddayDate;
        this.ddayModifyButton = lang.ddayModifyButton;
        this.ddayAddMemberButton = lang.ddayAddMemberButton;
        this.ddayNextButton = lang.ddayNextButton;
        this.ddayTitleAddDDayMember = lang.ddayTitleAddDDayMember;
        this.ddayTitleDDayModification = lang.ddayTitleDDayModification;
        this.ddayContactName = lang.ddayContactName;
        this.ddayContactNumber = lang.ddayContactNumber;
        this.ddayReceivedName = lang.ddayReceivedName;
        this.ddayReceivedBank = lang.ddayReceivedBank;
        this.ddayReceiveAccount = lang.ddayReceiveAccount;

        //Text -> Splitor Location
        this.locationVisitedText = lang.locationVisitedText;
        this.locationStoreName = lang.locationStoreName;
        this.locationNameEditText = lang.locationNameEditText;
        this.locationTotalMoney = lang.locationTotalMoney;
        this.locationMoneyEditText = lang.locationMoneyEditText;
        this.locationMemberDeleteButton = lang.locationMemberDeleteButton;
        this.locationAddMemberButton = lang.locationAddMemberButton;
        this.locationDetailOptionButton = lang.locationDetailOptionButton;
        this.locationSetManagerButton = lang.locationSetManagerButton;
        this.addMoreLocationButton = lang.addMoreLocationButton;
        this.locationNextButton = lang.locationNextButton;
        this.titleLocationMemberOption = lang.titleLocationMemberOption;

        //Text -> Splitor Result
        this.resultOfCalc = lang.resultOfCalc;
        this.resultTextView1 = lang.resultTextView1;
        this.resultTextView2 = lang.resultTextView2;
        this.copyResultButton1 = lang.copyResultButton1;
        this.copyResultButton2 = lang.copyResultButton2;
        this.resultSaveButton = lang.resultSaveButton;
        this.resultDoneButton = lang.resultDoneButton;

        //Text -> Splitor Result outputWriting
        this.resultOfThisMember = lang.resultOfThisMember;
        this.resultTo = lang.resultTo;
        this.resultToBeSent = lang.resultToBeSent;

        this.resultDDayName = lang.resultDDayName;
        this.resultDate = lang.resultDate;
        this.resultLocationName = lang.resultLocationName;
        this.resultLocationMoney = lang.resultLocationMoney;
        this.resultHost = lang.resultHost;
        this.resultTotalMember = lang.resultTotalMember;

        //Text -> Splitor Add new squad(ans)
        this.ansAddNewSquadText = lang.ansAddNewSquadText;
        this.ansSquadNameText = lang.ansSquadNameText;
        this.ansSquadMemberListText = lang.ansSquadMemberListText;
        this.ansSquadNameEditText = lang.ansSquadNameEditText;
        this.ansContactAddButton = lang.ansContactAddButton;
        this.ansAddMemberButton = lang.ansAddMemberButton;
        this.ansDeleteButton = lang.ansDeleteButton;
        this.ansNewSquadButton = lang.ansNewSquadButton;

        //Text -> Splitor Add member directly (amd)
        this.amdAddMemberText = lang.amdAddMemberText;
        this.amdNameText = lang.amdNameText;
        this.amdBankNameText = lang.amdBankNameText;
        this.amdBankAccountText = lang.amdBankAccountText;
        this.amdRemarkText = lang.amdRemarkText;
        this.amdNameEditText = lang.amdNameEditText;
        this.amdBankNameEditText = lang.amdBankNameEditText;
        this.amdBankAccountEditText = lang.amdBankAccountEditText;
        this.amdRemarkEditText = lang.amdRemarkEditText;
        this.amdCancelButton = lang.amdCancelButton;
        this.amdAddButton = lang.amdAddButton;

        //Text -> Splitor Location member add (lma)
        this.lmaAddUsingDDayMemText = lang.lmaAddUsingDDayMemText;
        this.lmaCancelButton = lang.lmaCancelButton;
        this.lmaAddButton = lang.lmaAddButton;

        //Alert
        this.selectMemberFirst = lang.selectMemberFirst;

    }

}
