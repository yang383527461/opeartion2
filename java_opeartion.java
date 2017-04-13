/**
 * Created by Administrator on 2017/4/7.
 */
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.exit;

public class java_opeartion {
    private int i=0,j=0;
    private  ArrayList<String> list=new ArrayList<String>();  //存算式

    Scanner op=new Scanner(System.in);
    String[] re1=new String[2];
    String[] re2=new String[2];
    int r1,r2,r3,r4;

    String huajian(String text){             //化简

        if(text.indexOf("/")!=-1) {
            re1 = text.split("/");
            r1 = Integer.parseInt(re1[0]);
            r2 = Integer.parseInt(re1[1]);

            if (r1 == 0)
                return "0";
            else {
                for (int i = 1; i <= r1; i++) {
                    if (r1 % i == 0 && r2 % i == 0) {
                        r1 = r1 / i;
                        r2 = r2 / i;
                        i = 1;
                    }
                    if (r2 == 1)
                        return r1 + "";
                }
                return r1+"/"+r2;
            }

        }
        else
            return text;



    }
    void leixing(){                    //输出
        int temp;
        int p1,p2,p3,p4;
        String form1="",form2="",form3="";
        char[] c={'+','-','*','/'};
        temp=(int)(Math.random()*2);
        Random random=new Random();
        int index = random.nextInt(c.length);
        while(true) {
            if (temp == 0) {
                p1 = (int) (Math.random() * 50);
                p2 = (int) (Math.random() * 50);
                form1 = String.valueOf(p1);
                form2 = String.valueOf(p2);

                System.out.print(form1+" ");
                System.out.print(c[index]);
                System.out.print(" "+form2);


/*            opear(Integer.toString(p1),Integer.toString(p2),index);*/
            }
            if (temp == 1) {
                p1 = (int) (Math.random() * 50);
                p2 = (int) (Math.random() * 50 + 2);
                p3 = (int) (Math.random() * p2 + 1);
                form1 = String.valueOf(p1);
                form2 = huajian(p3 + "/" + p2);

                System.out.print(form1+" ");
                System.out.print(c[index]);
                System.out.print(" " + form2 + " ");



/*            opear(Integer.toString(p1),huajian(p3+"/"+p2),index);*/
            }
            if (temp == 2) {
                p1 = (int) (Math.random() * 50 + 2);
                p2 = (int) (Math.random() * p1 + 1);
                p3 = (int) (Math.random() * 50 + 2);
                p4 = (int) (Math.random() * p3 + 1);
                form1 = huajian(p2 + "/" + p1);
                form2 = huajian(p4 + "/" + p3);

                System.out.print(" " + form1 + " ");
                System.out.print(c[index]);
                System.out.print(" " + form2 + " ");


/*            opear(huajian(p2+"/"+p1),huajian(p4+"/"+p3),index);*/
            }
            form3 = form1 + " "+c[index] +" "+ form2;
            list.add(form3);
            opear(form1, form2, index);
            int x = judge(form3);

            if(x==0)
                break;
        }
    }
     void opear(String x,String y,int index){          //  计算
        int n1,n2,n3,n4,n5,n6;
        String result="";
        String[] aa=new String[2];
        String[] bb=new String[2];
        String[] cc=new String[2];


            if(x.indexOf("/")==-1 && y.indexOf("/")==-1){
                int a=Integer.parseInt(x);
                int b=Integer.parseInt(y);
                if(index==0)
                    result=String.valueOf(a+b);
                if(index==1)
                    result=String.valueOf(a-b);
                if(index==2)
                    result=String.valueOf(a*b);
                if(index==3)
                    result=huajian(a+"/"+b);
            }
            if(x.indexOf("/")==-1 && y.indexOf("/")!=-1){
                int a=Integer.parseInt(x);

                aa=y.split("/");
                n1=Integer.parseInt(aa[0]);
                n2=Integer.parseInt(aa[1]);

                if(index==0)
                    result=huajian((a*n2+n1)+"/"+n2);
                if(index==1)
                    result=huajian((a*n2-n1)+"/"+n2);
                if(index==2)
                    result=huajian((a*n1)+"/"+n2);
                if(index==3)
                    result=huajian((a*n2)+"/"+n1);
            }

            if(x.indexOf("/")!=-1&&y.indexOf("/")!=-1) {
                aa = x.split("/");
                bb = y.split("/");
                n1 = Integer.parseInt(aa[0]);
                n2 = Integer.parseInt(aa[1]);
                n3 = Integer.parseInt(bb[0]);
                n4 = Integer.parseInt(bb[1]);

                if (index == 0)
                    result = huajian((n1 * n4 + n3 * n2) + "/" + (n2 * n4));
                if (index == 1)
                    result = huajian((n1 * n4 - n3 * n2) + "/" + (n2 * n4));
                if (index == 2)
                    result = huajian((n1 * n3) + "/" + (n2 * n4));
                if (index == 3)
                    result = huajian((n1 * n4) + "/" + (n2 * n3));
            }


        System.out.print("=");

        while(true) {
            String sum = op.nextLine();
            i++;
            if (isNumeric(sum) == false) {
                if (sum.equals("exit"))                       //输入exit 结束程序
                    exit(0);
                else
                    System.out.print("请输入规范!请重新输入答案=");

            } else {
                System.out.print("=" + result);
                if (result.equals(sum)) {
                    System.out.print("	结果正确    ");
                    j++;
                } else
                    System.out.print("	结果错误    ");
                System.out.println("准确率" + j + "/" + i);
                System.out.println(list);
                break;
            }
        }
    }
    boolean isNumeric(String str) {     //判断是否为数字或分数
        String[] o=new String[2];
        for (int i = 0;i<str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                if(String.valueOf(str.charAt(i)).equals("/")||String.valueOf(str.charAt(i)).equals("-"))
                {
                    o=str.split("/");
                    if((String.valueOf(str.charAt(i)).equals("/")&&o[1].equals("0"))
                            ||String.valueOf(str.charAt(0)).equals("/")
                            ||(!String.valueOf(str.charAt(0)).equals("-"))&&String.valueOf(str.charAt(i)).equals("-"))
                    {
                        return false;
                    }
                }
                else if(!String.valueOf(str.charAt(i)).equals("/")&&!String.valueOf(str.charAt(i)).equals("-"))
                    return false;
            }
            if(str.indexOf(" ")!=-1)
                return false;
        }
        return true;
    }
    int judge(String form3){                //判断是否生成相同的算式
        int i;
        String[] aa=new String[2];
        String a,b;
        if(list.size()==1)
            return 0;
        else {
            for (i = 0; i < list.size() - 1; i++) {
                if (form3.equals(list.get(i)))
                    return 1;
                if (form3.indexOf("+") != -1) {
                    aa = form3.split("\\+");
                    a = aa[1] + aa[0];
                    if (a.equals(list.get(i)))
                        return 1;

                }
            }
        }
        return 0;
    }
    public static void main(String[] args) {
        java_opeartion aa = new java_opeartion();
        System.out.println("开始进入四则运算，输入exit退出");
        while(true)
        {
            aa.leixing();
        }

    }
}
