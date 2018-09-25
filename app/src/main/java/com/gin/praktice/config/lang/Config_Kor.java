package com.gin.praktice.config.lang;

public class Config_Kor extends Config_Language {

    public Config_Kor() {

        deleteSelectedSquad = "선택모임 삭제";
        deleteSelectedMemberInSelectedSquad = "선택모임 선택멤버 삭제";
        addMemberToSelectedSquad = "선택모임에 새 멤버 추가";
        modifySelectedMemberName = "선택멤버 이름 수정";
        modifySelectedSquadName = "선택모임 이름 수정";

        deleteSelectedMemberFromDDay = "모임날에서 선택멤버 제거";
        addContactMemberToDDay = "모임날 멤버 주소록에서 추가";
        addRawMemberDirectly = "모임날 멤버 직접입력 추가";
        addKakaoFriend = "모임날 멤버 카카오톡에서 추가";

        littleLateMember = "선택멤버 조금 늦게옴";
        superLateMember = "선택멤버 많이 늦게옴";
        cancelLateMember = "선택멤버 늦은 것 취소";

        menuModification = "수정하기";
        menuAppend = "추가하기";
        menuRemove = "삭제하기";

        languageKor = "한국어";
        languageEng = "English";
        languageJpn = "Japanese";
        languageChn = "Chinese";
        languageFra = "French";
        languageGer = "German";

        //Text -> Main
        mainText = "정신차려 이 각박한 세상 속에서";
        mainSplitorButton = "엔빵하기";

        //Text -> Splitor Main
        splitorMainText = "정산 기능 - N분의 1";
        wholeGroupListText = "전체 모임 리스트";
        selectedMemberListText = "선택된 모임의 멤버들";
        splitorMainModifyButton = "수정하기";
        splitorMainNewSquadButton = "새로운 모임을 생성/저장";
        splitorMainNewDDayButton = "새로운 날 시작하기";

        //Text -> Splitor DDay
        ddayText = "모임날";
        ddayNameText = "날 이름";
        ddayNameEditText = "모인 날 이름을 정해보세요";
        ddayDate = "날짜";
        ddayModifyButton = "수정하기";
        ddayAddMemberButton = "모임날 멤버추가";
        ddayNextButton = "다음 단계로";

        //Text -> Splitor Location
        locationVisitedText = "들렀던 장소";
        locationStoreName = "가게 이름";
        locationNameEditText = "들렀던 가게 이름을 입력하세요";
        locationTotalMoney = "총 금액";
        locationMoneyEditText = "이 가게에서 쓴 금액을 입력하세요";
        locationMemberDeleteButton = "선택된 멤버\n제거";
        locationAddMemberButton = "이 장소\n멤버 설정";
        locationDetailOptionButton = "세부기능 \n추가";
        locationSetManagerButton = "선택된 멤버가\n이 장소의 총무";
        addMoreLocationButton = "이 장소를 저장하고\n다음 장소 더 추가하기";
        locationNextButton = "이제 그만\n여기가 마지막!!  결과보기";

        //Text -> Splitor Result
        resultOfCalc = "모임 정산 결과";
        resultTextView1 = "모임날 전체 결과와 각 장소별 멤버별 정산 세부내역";
        resultTextView2 = "각 장소 총무들 정보 및 부칠 액수";
        copyResultButton1 = "결과\n복사";
        copyResultButton2 = "결과\n복사";
        resultSaveButton = "(준비 중)";
        resultDoneButton = "메인화면으로 돌아갑니다";

        resultOfThisMember = "님의 정산결과는 \n";
        resultTo = " 에게 ";
        resultToBeSent = "원을 부쳐야합니다";

        //Alert msg
        selectMemberFirst = "멤버를 먼저 선택하세요";

    }

}
