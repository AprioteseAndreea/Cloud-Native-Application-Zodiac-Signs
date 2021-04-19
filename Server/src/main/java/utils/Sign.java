package utils;

public class Sign {
    private String zodiacSignName;
    private String begin;
    private String end;

    public Sign(String zodiacSignName, String begin, String end) {
        this.zodiacSignName = zodiacSignName;
        this.begin = begin;
        this.end = end;
    }

    public String getZodiacSignName() {
        return zodiacSignName;
    }

    public void setZodiacSignName(String zodiacSignName) {
        this.zodiacSignName = zodiacSignName;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getStartDay() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < begin.length(); i++) {
            if (begin.charAt(i) != '/') sb.append(begin.charAt(i));
            else break;
        }
        return Integer.parseInt(sb.toString());
    }

    public int getStartMonth()  {
        StringBuilder sb = new StringBuilder();
        int contor=0;
        for (int i = 0; i < begin.length(); i++) {
            if (begin.charAt(i) == '/')contor++;
            if(contor==1 && begin.charAt(i) != '/' )sb.append(begin.substring(i, begin.length()));
        }
        return Integer.parseInt(sb.toString());
    }

    public int getEndDay() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < end.length(); i++) {
            if (end.charAt(i) != '/') sb.append(end.charAt(i));
            else break;

        }
        return Integer.parseInt(sb.toString());
    }

    public int getEndMonth() {
        StringBuilder sb = new StringBuilder();
        int contor=0;
        for (int i = 0; i < end.length(); i++) {
            if (end.charAt(i) == '/')contor++;
            if(contor==1 && end.charAt(i) != '/' )sb.append(end.charAt(i));
        }
        return Integer.parseInt(sb.toString());
    }
}
