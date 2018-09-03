package com.gin.praktice.dynamic.result;

import com.gin.praktice.component.DDay;
import com.gin.praktice.component.Member;
import com.gin.praktice.member.DayMembers;

public enum MoneyMatrix {
    INSTANCE;

    public int[][] resultMoneyMat = new int[256][256];

    /**
     * Dynamic matrix for result money distribution.
     * Matrix size is big enough to add more member after dday acty,
     * but if you wanna remove after dday acty, then, need to add or change method later.
     *
     * @param money2Send
     * @param sendMember
     * @param receiveMember
     */
    public void setMoneyToMat(int money2Send, Member sendMember, Member receiveMember)
    {
        DayMembers dayMembers = DDay.getInstance().dayMembers;
        int sendMemberIndex = -1;
        int receiveMemberIndex = -1;

//        Log.d("Send", sendMember.getName());
//        Log.d("Receive", receiveMember.getName());
        for (int i = 0; i < dayMembers.getLength(); ++i)
        {
            Member member = (Member)dayMembers.get(i);
//            Log.d("dayMem", member.getName());

            if (member.equals(sendMember))
            {
                sendMemberIndex = i;
            }
            if (member.equals(receiveMember))
            {
                receiveMemberIndex = i;
            }
        }
        if (sendMemberIndex != receiveMemberIndex)
        {
            resultMoneyMat[sendMemberIndex][receiveMemberIndex] += money2Send;
            money2Send = resultMoneyMat[sendMemberIndex][receiveMemberIndex];
            int money2Receive = resultMoneyMat[receiveMemberIndex][sendMemberIndex];
            if (money2Receive > 0)
            {
                if (money2Send > money2Receive)
                {
                    resultMoneyMat[sendMemberIndex][receiveMemberIndex] = money2Send - money2Receive;
                    resultMoneyMat[receiveMemberIndex][sendMemberIndex] = 0;
                }
                else if (money2Send == money2Receive)
                {
                    resultMoneyMat[sendMemberIndex][receiveMemberIndex] = 0;
                    resultMoneyMat[receiveMemberIndex][sendMemberIndex] = 0;
                }
                else if (money2Send < money2Receive)
                {
                    resultMoneyMat[sendMemberIndex][receiveMemberIndex] = 0;
                    resultMoneyMat[receiveMemberIndex][sendMemberIndex] = money2Receive - money2Send;
                }
            }
        }
    }




}
