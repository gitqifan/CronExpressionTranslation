package qifan.utils;

import java.util.*;

/**
 * 将定时表达式翻译成汉语的工具类
 */
public final class CronTranslationUtil {

    public static String CronTranslation(String cron_str) {

        final Set<String> monthSet = new HashSet<String>();
        final Map<String, String> monthMap = new HashMap<String, String>();
        final Map<String, String> weekMap  = new HashMap<String, String>();

        monthSet.add("1");
        monthSet.add("2");
        monthSet.add("3");
        monthSet.add("4");
        monthSet.add("5");
        monthSet.add("6");
        monthSet.add("7");
        monthSet.add("8");
        monthSet.add("9");
        monthSet.add("10");
        monthSet.add("11");
        monthSet.add("12");

        monthMap.put("1", "1月");
        monthMap.put("2", "2月");
        monthMap.put("3", "3月");
        monthMap.put("4", "4月");
        monthMap.put("5", "5月");
        monthMap.put("6", "6月");
        monthMap.put("7", "7月");
        monthMap.put("8", "8月");
        monthMap.put("9", "9月");
        monthMap.put("10", "10月");
        monthMap.put("11", "11月");
        monthMap.put("12", "12月");

        weekMap.put("SUN", "星期天");
        weekMap.put("MON", "星期一");
        weekMap.put("TUE", "星期二");
        weekMap.put("WED", "星期三");
        weekMap.put("THU", "星期四");
        weekMap.put("FRI", "星期五");
        weekMap.put("SAT", "星期六");

        weekMap.put("1", "星期天");
        weekMap.put("2", "星期一");
        weekMap.put("3", "星期二");
        weekMap.put("4", "星期三");
        weekMap.put("5", "星期四");
        weekMap.put("6", "星期五");
        weekMap.put("7", "星期六");

        weekMap.put("L",  "星期天");
        weekMap.put("2L", "星期一");
        weekMap.put("3L", "星期二");
        weekMap.put("4L", "星期三");
        weekMap.put("5L", "星期四");
        weekMap.put("6L", "星期五");
        weekMap.put("7L", "星期六");

        cron_str = cron_str.trim();
        if ((cron_str == null) || (cron_str.isEmpty() )) {
            return "";
        }

        List<String> list_c = Arrays.asList(cron_str.split(" "));
        int num_string = list_c.size();

        String out6 = "";
        String out5 = "";
        String out4 = "";
        String out3 = "";
        String out2 = "";
        String out1 = "";
        String out0 = "";

        if (num_string != 6 && num_string != 7) {
            return "表达式错误";
        }
        else { // 6个字符 或 7个字符

            String c_0 = list_c.get(0); // 秒
            String c_1 = list_c.get(1); // 分钟
            String c_2 = list_c.get(2); // 小时
            String c_3 = list_c.get(3); // 几号
            String c_4 = list_c.get(4); // 月
            String c_5 = list_c.get(5); // 星期
            String c_6 = "";            // 年
            if (num_string == 7) {
                c_6 = list_c.get(6);
            }

            boolean mw_m = false;
            boolean hms_h = false;
            boolean hms_m = false;
            boolean hms_s = false;

            if (c_6.isEmpty() == false) {
                if (c_6.equals("*") || c_6.equals("?")) {
                    out6 += "";
                } else if (c_6.contains("/")) {
                    String next = c_6.substring(c_6.indexOf("/") + 1, c_6.indexOf("/") + 2);
                    out6 += "每" + next + "年";
                } else if (c_6.contains("-")) {
                    String s1 = c_6.substring(0, c_6.indexOf("-"));
                    String s2 = c_6.substring(c_6.indexOf("-") + 1, c_6.length());
                    out6 += s1 + "至" + s2 + "年";
                } else if (c_6.contains(",")) {
                    out6 += "在" + c_6 + "年";
                } else {
                    out6 += c_6 + "年";
                }
            }

            if (c_5.equals("*") || c_5.equals("?")) {
                out5 += "";
            } else if (c_5.contains("/")) {
                String next = c_5.substring(c_5.indexOf("/") + 1, c_5.indexOf("/") + 2);
                out5 += "每" + next + "个星期";
            } else if (c_5.contains("-")) {
                String s1 = c_5.substring(0, c_5.indexOf("-"));
                String s2 = c_5.substring(c_5.indexOf("-") + 1, c_5.length());
                out5 += weekMap.get(s1) + "至" + weekMap.get(s2) + "的";
            } else if (c_5.contains("#")) {
                String s1 = c_5.substring(0, c_5.indexOf("#"));
                String s2 = c_5.substring(c_5.indexOf("#") + 1, c_5.length());
                out5 += "每月第" + s2 + "个" + weekMap.get(s1) + "的";
            } else if (c_5.contains(",")) {
                List<String> list_week = Arrays.asList(c_5.split(","));
                int list_week_size = list_week.size();
                for (int i = 0; i < list_week_size - 1; i++) {
                    out5 += weekMap.get(list_week.get(i)) + ",";
                }
                out5 += weekMap.get(list_week.get(list_week_size - 1));
                mw_m = true;
            } else if (c_5.equals("7L") || c_5.equals("6L") || c_5.equals("5L") || c_5.equals("4L") || c_5.equals("3L") || c_5.equals("2L") || c_5.equals("L")) {
                out5 += "每月最后一个" + weekMap.get(c_5) + "的";
            } else if ((c_5.length() == 3) && (c_5.equals("SUN") || c_5.equals("MON") || c_5.equals("TUE") || c_5.equals("WED") || c_5.equals("THU") || c_5.equals("FRI") | c_5.equals("SAT"))) {
                out5 += weekMap.get(c_5);
                mw_m = true;
            } else {
                out5 += c_5;
            }

            if (c_4.equals("*") || c_4.equals("?")) {
                out4 += "";
            } else if (c_4.contains("/")) {
                String next = c_4.substring(c_4.indexOf("/") + 1, c_4.indexOf("/") + 2);
                out4 += "每" + next + "个月";
            } else if (c_4.contains("-")) {
                String s1 = c_4.substring(0, c_4.indexOf("-"));
                String s2 = c_4.substring(c_4.indexOf("-") + 1, c_4.length());
                out4 += monthMap.get(s1) + "至" + monthMap.get(s2);
            } else if (c_4.contains(",")) {
                if (mw_m == true) {
                    out4 = c_4 + "月的" + out5 + "的";
                    out5 = "";
                }
            } else if (monthSet.contains(c_4)) {
                if (mw_m == true) {
                    out4 = monthMap.get(c_4) + "的每个" + out5 + "的";
                    out5 = "";
                } else {
                    out4 += monthMap.get(c_4);
                }
            }

            if (c_3.equals("*") || c_3.equals("?")) {
                out3 += "";
            } else if (c_3.contains("/")) {
                String next = c_3.substring(c_3.indexOf("/") + 1, c_3.indexOf("/") + 2);
                out3 += "每" + next + "天";
            } else if (c_3.contains("-")) {
                String s1 = c_3.substring(0, c_3.indexOf("-"));
                String s2 = c_3.substring(c_3.indexOf("-") + 1, c_3.length());
                out3 += s1 + "号至" + s2 + "号";
            } else if (c_3.contains(",")) {
                out3 += "" + c_3 + "号的";
            } else if (c_3.equals("L")) {
                out3 += "最后一天的";
            } else if ((c_3.compareTo("0") >= 0) && (c_3.compareTo("31") <= 0)) {
                out3 += c_3 + "号的";
            }

            if (c_2.equals("*") || c_2.equals("?")) {
                out2 += "";
            } else if (c_2.contains("/")) {
                String next = c_2.substring(c_2.indexOf("/") + 1, c_2.indexOf("/") + 2);
                out2 += "每" + next + "个小时";
            } else if (c_2.contains("-")) {
                String s1 = c_2.substring(0, c_2.indexOf("-"));
                String s2 = c_2.substring(c_2.indexOf("-") + 1, c_2.length());
                out2 += s1 + "点至" + s2 + "点的";
            } else if (c_2.contains(",")) {
                out2 += "" + c_2 + "点的";
            } else if ((c_2.compareTo("0") >= 0) && (c_2.compareTo("23") <= 0)) {
                out2 += c_2 + "点的";
                hms_h = true;
            }

            if (c_1.equals("*") || c_1.equals("?")) {
                out1 += "每分钟的";
            } else if (c_1.contains("/")) {
                String next = c_1.substring(c_1.indexOf("/") + 1, c_1.indexOf("/") + 2);
                out1 += "每" + next + "分钟的";
            } else if (c_1.contains("-")) {
                String s1 = c_1.substring(0, c_1.indexOf("-"));
                String s2 = c_1.substring(c_1.indexOf("-") + 1, c_1.length());
                out1 += "第" + s1 + "至" + s2 + "分钟的";
            } else if (c_1.contains(",")) {
                out1 += "第" + c_1 + "分钟的";
            } else if ((c_1.compareTo("0") >= 0) && (c_1.compareTo("59") <= 0)) {
                out1 += "第" + c_1 + "分钟的";
                hms_m = true;
            }

            if (c_0.equals("*") || c_0.equals("?")) {
                out0 += "每秒";
            } else if (c_0.contains("/")) {
                String next = c_0.substring(c_0.indexOf("/") + 1, c_0.indexOf("/") + 2);
                out0 += "每" + next + "秒";
            } else if (c_0.contains("-")) {
                String s1 = c_0.substring(0, c_0.indexOf("-"));
                String s2 = c_0.substring(c_0.indexOf("-") + 1, c_0.length());
                out0 += s1 + "秒至" + s2 + "秒";
            } else if (c_0.contains(",")) {
                out0 += "第" + c_0 + "秒";
            } else if ((c_0.compareTo("0") >= 0) && (c_0.compareTo("59") <= 0)) {
                out0 += "第" + c_0 + "秒";
                hms_s = true;
            }

            if ((hms_h == true) && (hms_m == true) && (hms_s == true)) {
                if (c_2.length() == 1) {
                    out2 = "0" + c_2 + ":";
                } else {
                    out2 = c_2 + ":";
                }

                if (c_1.length() == 1) {
                    out1 = "0" + c_1 + ":";
                } else {
                    out1 = c_1 + ":";
                }

                if (c_0.length() == 1) {
                    out0 = "0" + c_0;
                } else {
                    out0 = c_0;
                }
            }

            String front_preoid = "";
            boolean w = false;
            boolean mon = false;
            boolean d = false;
            boolean h = false;
            boolean min = false;

            String ret_str = "";

            if (c_6.isEmpty()) {
                if (c_5.equals("*") || c_5.equals("?")) {
                    front_preoid = "每星期的";
                    w = true;
                } else if (c_5.contains("#") || c_5.contains("L")) {
                    front_preoid = "";
                    w = true;
                }

                if (w && (c_4.equals("*") || c_4.equals("?")) && !c_5.contains("#") && !c_5.contains("L")) {
                    front_preoid = "每月";
                    mon = true;
                }
                if (mon && (c_3.equals("*") || c_3.equals("?")) && !c_5.contains("#") && !c_5.contains("L")) {
                    front_preoid = "每天";
                    d = true;
                }
                if (d && (c_2.equals("*") || c_2.equals("?"))) {
                    front_preoid = "每小时的";
                    h = true;
                }
                if (h && (c_1.equals("*") || c_1.equals("?"))) {
                    front_preoid = "每分钟的";
                    min = true;
                }
                if (min && (c_0.equals("*") || c_0.equals("?"))) {
                    front_preoid = "每秒";
                }

                String out = out5 + out4 + out3 + out2 + out1 + out0;

                if (!front_preoid.isEmpty() && out.contains(front_preoid)) {
                    front_preoid = "";
                }

                if (front_preoid.isEmpty() && !c_5.contains("#") && !c_5.contains("L") && (out.indexOf("每") != 0)) {
                    front_preoid = "每年";
                }

                ret_str = front_preoid + out6 + out;
            }
            else {
                if ((c_6.equals("*") || c_6.equals("?"))) {
                    front_preoid = "每年";
                }

                if (c_5.equals("*") || c_5.equals("?")) {
                    front_preoid = "每星期的";
                    w = true;
                } else if (c_5.contains("#") || c_5.contains("L")) {
                    front_preoid = "";
                    w = true;
                }

                if (w && (c_4.equals("*") || c_4.equals("?")) && !c_5.contains("#") && !c_5.contains("L")) {
                    front_preoid = "每月";
                    mon = true;
                }
                if (mon && (c_3.equals("*") || c_3.equals("?")) && !c_5.contains("#") && !c_5.contains("L")) {
                    front_preoid = "每天";
                    d = true;
                }
                if (d && (c_2.equals("*") || c_2.equals("?"))) {
                    front_preoid = "每小时的";
                    h = true;
                }
                if (h && (c_1.equals("*") || c_1.equals("?"))) {
                    front_preoid = "每分钟的";
                    min = true;
                }
                if (min && (c_0.equals("*") || c_0.equals("?"))) {
                    front_preoid = "每秒";
                }

                String out = out5 + out4 + out3 + out2 + out1 + out0;

                if (out.contains(front_preoid)) {
                    front_preoid = "";
                }

                ret_str = out6 + front_preoid + out;
            }

            return ret_str;
        }

    }

}