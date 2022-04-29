package my_stringbuffer;

/**
 * @author 86187
 */
public final class MyStringBuffer {
    private char[] values;

    private int capacity = 16;

    private int length = 0;

    private static final float load = 0.75F;

    public MyStringBuffer(){
        this.values = new char[capacity];
    }

    public MyStringBuffer(int capacity){
        this.capacity = capacity;
        this.values = new char[capacity];
    }

    public MyStringBuffer(String s){
        if(s == null){
            NullPointerException e = new NullPointerException("请不要传入空");
            throw e;
        }
        int len = s.length();
        if(len != 0){
            this.length = len;
            toBigger();
            char[] temp = s.toCharArray();
            System.arraycopy(temp, 0, this.values, 0, length);
        }
    }

    public MyStringBuffer(CharSequence s){
        this(s.toString());
    }

    private void toBigger(){
        if (length != 0) {
            while (length >= capacity * load) {
                this.capacity += this.capacity * load;
            }
            char[] temp = new char[capacity];
            if(this.values != null){
                System.arraycopy(values, 0, temp, 0, length);
            }
            this.values = temp;
        }
    }

    public int capacity(){
        return this.capacity;
    }

    public int length(){
        return this.length;
    }

    @Override
    public String toString(){
        if(values == null){
            throw new NullPointerException("当前values为空");
        }
        return String.valueOf(this.values, 0, length);
    }

    public void ensureCapacity(int minimumCapacity){
        this.capacity = minimumCapacity;
        char[] temp = new char[capacity];
        int min = Math.min(minimumCapacity, this.length);
        if(values != null){
            System.arraycopy(values, 0, temp, 0, min);
        }
        this.length = min ;
        this.values = temp;
    }

    public void append(String s){
        if(s == null){
            NullPointerException e = new NullPointerException("请不要传入空");
            throw e;
        }
        int len = s.length();
        this.length += len;
        toBigger();
        char[] chars = s.toCharArray();
        System.arraycopy(chars, 0, values, this.length - len, len);
    }

    public void delete(int star, int end){
        if(star < 0 || end < 0 || star >= length || end > length || star > end){
            throw new IndexOutOfBoundsException("star:" + star + "   "+ "end:" + end + "   " + "length:" + length);
        }
        int flow = 0, fast;
        for( fast = 0; fast < length; fast ++){
            if(fast < star || fast >= end){
                values[flow++] = values[fast];
            }
        }
        this.length -= end - star;
    }

    public int indexOf(String s){
        if(s == null){
            throw new NullPointerException("请不要传入空");
        }
        int len = s.length();
        if(length == 0){
            if(len == 0){
                return 0;
            }
            return -1;
        }
        int res = -1;
        int[] next = new int[len];
        char[] chars = s.toCharArray();
        for(int i = 1, j = 0; i < len; i ++){
            while (j > 0 && chars[i] != chars[j]){
                j = next[j - 1];
            }
            if(chars[i] == chars[j]){
                j ++;
            }
            next[i] = j;
        }
        for(int i = 0 , j = 0; i < length ; i ++){
            while (j > 0 && values[i] != chars[j]){
                j = next[j - 1];
            }
            if(values[i] == chars[j]){
                j++;
                if(j == 1){
                    res = i;
                }
                if(j == len){
                    return res;
                }
            }
        }
        return -1;
    }

    public int lastIndexOf(String s){
        if(s == null){
            throw new NullPointerException("请不要传入空值");
        }
        int len = s.length();
        if(len == 0){
            if(length == 0){
                return 0;
            }
            return length - 1;
        }
        char[] chars = s.toCharArray();
        int i = length - 1, j = len - 1;
        while (i >= 0){
            if( values[i] == chars[j]){
                i--;
                j--;
                if(j < 0){
                    return i + 1;
                }
            }
            else {
                i--;
                j = len - 1;
            }
        }
        return -1;
    }

    public void reverse(){
        if(values == null){
            throw new NullPointerException("空的values不能反转");
        }
        if(length != 0){
            int l = 0, r = length - 1;
            while (l < r){
                char temp = values[l];
                values[l] = values[r];
                values[r] = temp;
                r--;
                l++;
            }
        }
    }

    public String subString(int star , int end){
        if(star < 0 || end < 0 || star >= length || end > length || star > end){
            throw new IndexOutOfBoundsException("star:" + star + "   "+ "end:" + end + "   " + "length:" + length);
        }
        return String.valueOf(values, star, end - star);
    }

    public void replace (int star, int end, String s){
        if(star < 0 || end < 0 || star >= length || end > length || star > end){
            throw new IndexOutOfBoundsException("star:" + star + "   "+ "end:" + end + "   " + "length:" + length);
        }
        if(s == null){
            throw new NullPointerException("s为空!");
        }
        char[] chars1 = new char[length - end];
        System.arraycopy(values, end, chars1, 0, length - end);
        char[] chars = s.toCharArray();
        int len = s.length();
        this.length =this.length +  len - (end - star);
        toBigger();
        char[] temp = new char[length];
        for(int i = 0; i < star ; i++){
            temp[i] = values[i];
        }
        for(int i = star, j = 0; i < star + len; i++, j++){
            temp[i] = chars[j];
        }
        for(int i = star + len, j = 0; i < length; i++, j++){
            temp[i] = chars1[j];
        }
        this.values = temp;
    }

    public void append(byte n){
        String s = n + "";
        append(s);
    }

    public void append(short n){
        String s = n + "";
        append(s);
    }

    public void append(int n){
        String s = n + "";
        append(s);
    }

    public void append(long n){
        String s = n + "";
        append(s);
    }

    public void append(float n){
        String s = n + "";
        append(s);
    }

    public void append(double n){
        String s = n + "";
        append(s);
    }

    public void append(char n){
        String s = n + "";
        append(s);
    }

    public void append(boolean n){
        String s = n + "";
        append(s);
    }

    public void append(StringBuffer n){
        String s = n + "";
        append(s);
    }

    public void append(StringBuilder n){
        String s = n + "";
        append(s);
    }

    public boolean isEmpty(){
        return length == 0 ;
    }
}
