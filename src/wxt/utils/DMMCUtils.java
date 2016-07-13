package wxt.utils;

/**
 * Created by wuxiutong on 2015/8/18.
 */
public class DMMCUtils {
    //返回代码
    public static String getDM(String string,String prefix){
        if(null != string){
            int i = string.indexOf(prefix);
            if(i>=0)
            {
                if(string.indexOf(prefix)<0){
                    return string;
                }else
                return  string.substring(0,string.indexOf(prefix));
            }else {
                return string;
            }
        }else
        return null;
    }
    //返回第一个前缀到最后的后缀之间的内容
    public static String getFirst2LastMC(String string, String prefix, String suffix) {
        //如果包含前缀则执行如下，否则返回NULL
        if (string.contains(prefix)) {
            //如果包含后缀则执行 否则直接返回前缀以后的所有字符
            if (string.contains(suffix)) {
                //如果前缀的位置在后缀的位置之前，否则直接返回前缀以后的所有字符
                if (string.indexOf(prefix) + prefix.length() <= string.lastIndexOf(suffix)) {
                    return string.substring(string.indexOf(prefix) + prefix.length(), string.lastIndexOf(suffix));
                } else {
                    return string.substring(string.indexOf(prefix) + prefix.length());
                }
            } else {
                return string.substring(string.indexOf(prefix) + prefix.length());
            }
        }
        return null;
    }

    //返回指定第几个前缀到该前缀匹配之间的内容
    public static String getDesignMC(String string, String prefix, String suffix, int index) {
        int total = index;
        int prefixIndex = 0;
        int suffixIndex = -1;
        String str = string;
        boolean isok = true;
        //如果包含前缀则执行如下，否则返回NULL
        if (str.contains(prefix)) {
            //如果包含后缀则执行 否则直接返回前缀以后的所有字符
            if (str.contains(suffix)) {
                //如果前缀的位置在后缀的位置之前，否则直接返回前缀以后的所有字符
                while (total > 0) {
                    if (str.contains(prefix)) {
                        str = str.substring(str.indexOf(prefix) + prefix.length());
                        // System.out.println("str:" + str);
                        total--;
                    } else return null;
                }

                suffixIndex = 0;
                int j = 0; //后缀个数
                int i = 0;//前缀个数

                //System.out.println("截取后：:" + str);
                if(str.indexOf(prefix)>=0 && str.indexOf(prefix)<str.indexOf(suffix)) {
                    for (int i1 = str.indexOf(prefix); i1 < str.length();) {
                        // System.out.println("i1:"+i1);
                        if (str.indexOf(prefix, i1) == i1) {
                            i++;
                            i1+=prefix.length();
                        } else if (str.indexOf(suffix, i1) == i1) {
                            j++;
                            i1+=suffix.length();
                        }else{
                            i1++;
                        }
                        if (j == i) {
                            return str.substring(0, i1);
                        }
                        // System.out.println(j+":"+i);
                    }
                }else {
                    return str.substring(0,str.indexOf(suffix));
                }
            } else {
                return string.substring(string.indexOf(prefix) + prefix.length());
            }
        }
        return null;
    }
}
