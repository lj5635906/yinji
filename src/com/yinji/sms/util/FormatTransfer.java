package com.yinji.sms.util;

public class FormatTransfer {
	public static byte[] longToBytes(long n) {
		byte[] b = new byte[8];
		b[7] = (byte) (n & 0xff);
		b[6] = (byte) (n >> 8 & 0xff);
		b[5] = (byte) (n >> 16 & 0xff);
		b[4] = (byte) (n >> 24 & 0xff);
		b[3] = (byte) (n >> 32 & 0xff);
		b[2] = (byte) (n >> 40 & 0xff);
		b[1] = (byte) (n >> 48 & 0xff);
		b[0] = (byte) (n >> 56 & 0xff);
		return b;
	}

	public static void longToBytes(long n, byte[] array, int offset) {
		array[7 + offset] = (byte) (n & 0xff);
		array[6 + offset] = (byte) (n >> 8 & 0xff);
		array[5 + offset] = (byte) (n >> 16 & 0xff);
		array[4 + offset] = (byte) (n >> 24 & 0xff);
		array[3 + offset] = (byte) (n >> 32 & 0xff);
		array[2 + offset] = (byte) (n >> 40 & 0xff);
		array[1 + offset] = (byte) (n >> 48 & 0xff);
		array[0 + offset] = (byte) (n >> 56 & 0xff);

	}

	public static long bytesToLong(byte[] array) {
		return ((((long) array[0] & 0xff) << 56)
				| (((long) array[1] & 0xff) << 48)
				| (((long) array[2] & 0xff) << 40)
				| (((long) array[3] & 0xff) << 32)
				| (((long) array[4] & 0xff) << 24)
				| (((long) array[5] & 0xff) << 16)
				| (((long) array[6] & 0xff) << 8) | (((long) array[7] & 0xff) << 0));
	}

	public static long bytesToLong(byte[] array, int offset) {
		return ((((long) array[0 + offset] & 0xff) << 56)
				| (((long) array[1 + offset] & 0xff) << 48)
				| (((long) array[2 + offset] & 0xff) << 40)
				| (((long) array[3 + offset] & 0xff) << 32)
				| (((long) array[4 + offset] & 0xff) << 24)
				| (((long) array[5 + offset] & 0xff) << 16)
				| (((long) array[6 + offset] & 0xff) << 8) | (((long) array[7 + offset] & 0xff) << 0));
	}

	public static byte[] intToBytes(int n) {
		byte[] b = new byte[4];
		b[3] = (byte) (n & 0xff);
		b[2] = (byte) (n >> 8 & 0xff);
		b[1] = (byte) (n >> 16 & 0xff);
		b[0] = (byte) (n >> 24 & 0xff);
		return b;
	}

	public static void intToBytes(int n, byte[] array, int offset) {
		array[3 + offset] = (byte) (n & 0xff);
		array[2 + offset] = (byte) (n >> 8 & 0xff);
		array[1 + offset] = (byte) (n >> 16 & 0xff);
		array[0 + offset] = (byte) (n >> 24 & 0xff);

	}

	public static int bytesToInt(byte[] array) {
		return ((((int) array[0] & 0xff) << 24)
				| (((int) array[1] & 0xff) << 16)
				| (((int) array[2] & 0xff) << 8) | (((int) array[3] & 0xff)));
	}

	public static int bytesToInt(byte[] array, int offset) {
		return (((int) (array[0 + offset] & 0xff) << 24)
				| ((int) (array[1 + offset] & 0xff) << 16)
				| ((int) (array[2 + offset] & 0xff) << 8) | ((int) (array[3 + offset] & 0xff)));
	}

	// //////////////////////////////////////////
	public static byte[] uintToBytes(long n) {
		byte[] b = new byte[4];
		b[3] = (byte) (n & 0xff);
		b[2] = (byte) (n >> 8 & 0xff);
		b[1] = (byte) (n >> 16 & 0xff);
		b[0] = (byte) (n >> 24 & 0xff);
		return b;
	}

	public static void uintToBytes(long n, byte[] array, int offset) {
		array[3 + offset] = (byte) (n & 0xff);
		array[2 + offset] = (byte) (n >> 8 & 0xff);
		array[1 + offset] = (byte) (n >> 16 & 0xff);
		array[0 + offset] = (byte) (n >> 24 & 0xff);

	}

	public static long bytesToUint(byte[] array) {
		return ((((long) array[0] & 0xff) << 24)
				| (((long) array[1] & 0xff) << 16)
				| (((long) array[2] & 0xff) << 8) | (((long) array[3] & 0xff)));
	}

	public static long bytesToUint(byte[] array, int offset) {
		return (((long) (array[0 + offset] & 0xff) << 24)
				| ((long) (array[1 + offset] & 0xff) << 16)
				| ((long) (array[2 + offset] & 0xff) << 8) | ((long) (array[3 + offset] & 0xff)));
	}

	// /////////////////
	public static byte[] shortToBytes(short n) {
		byte[] b = new byte[2];
		b[1] = (byte) (n & 0xff);
		b[0] = (byte) (n >> 8 & 0xff);
		return b;
	}

	public static void shortToBytes(short n, byte[] array, int offset) {
		array[1 + offset] = (byte) (n & 0xff);
		array[offset] = (byte) (n >> 8 & 0xff);

	}

	public static short bytesToShort(byte[] array) {
		return (short) (((array[0] & 0xff) << 8 | (array[1] & 0xff)));
	}

	public static short bytesToShort(byte[] array, int offset) {
		return (short) (((array[offset] & 0xff) << 8 | (array[offset + 1] & 0xff)));
	}

	// ///////////////////////////

	public static byte[] ushortToBytes(short n) {
		byte[] b = new byte[2];
		b[1] = (byte) (n & 0xff);
		b[0] = (byte) (n >> 8 & 0xff);
		return b;
	}

	public static void ushortToBytes(short n, byte[] array, int offset) {
		array[1 + offset] = (byte) (n & 0xff);
		array[0 + offset] = (byte) (n >> 8 & 0xff);

	}

	public static short bytesToUshort(byte[] array) {
		return (short) ((array[0] & 0xff) << 8 | (array[1] & 0xff));
	}

	public static short bytesToUshort(byte[] array, int offset) {
		return (short) ((array[offset] & 0xff) << 8 | (array[offset + 1] & 0xff));
	}

	// ///////////////////////////
	public static byte[] ubyteToBytes(int n) {
		byte[] b = new byte[1];
		b[0] = (byte) (n & 0xff);
		return b;
	}

	public static void ubyteToBytes(int n, byte[] array, int offset) {
		array[offset] = (byte) (n & 0xff);
	}

	public static int bytesToUbyte(byte[] array) {
		return array[0] & 0xff;
	}

	public static int bytesToUbyte(int n, byte[] array, int offset) {
		return array[offset] & 0xff;
	}

	private static String hexStr = "0123456789ABCDEFabcdef";
	private static String[] binaryArray = { "0000", "0001", "0010", "0011",
			"0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011",
			"1100", "1101", "1110", "1111" };

	/**
	 * 
	 * @param str
	 * @return 转换为二进制字符串
	 */
	public static String bytes2BinaryStr(byte[] bArray) {

		String outStr = "";
		int pos = 0;
		for (byte b : bArray) {
			// 高四位
			pos = (b & 0xF0) >> 4;
			outStr += binaryArray[pos];
			// 低四位
			pos = b & 0x0F;
			outStr += binaryArray[pos];
		}
		return outStr;

	}

	/**
	 * 
	 * @param bytes
	 * @return 将二进制转换为十六进制字符输出
	 */
	public static String BinaryToHexString(byte[] bytes) {
		/**
		 * String result = ""; String hex = ""; for(int i=0;i<bytes.length;i++){
		 * //字节高4位 hex = String.valueOf(hexStr.charAt((bytes[i]&0xF0)>>4));
		 * //字节低4位 hex += String.valueOf(hexStr.charAt(bytes[i]&0x0F)); result
		 * +=hex+" "; } return result;
		 **/

		// StringBuilder sb = new StringBuilder(bytes.length * 2);
		//
		// Formatter formatter = new Formatter(sb);
		// for (byte b : bytes) {
		// formatter.format("%02x", b);
		// }
		//
		// return sb.toString();

		StringBuilder stringBuilder = new StringBuilder("");
		if (bytes == null || bytes.length <= 0) {
			return null;
		}
		for (int i = 0; i < bytes.length; i++) {
			int v = bytes[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	/**
	 * 
	 * @param hexString
	 * @return 将十六进制转换为字节数组
	 */
	public static byte[] HexStringToBinary(String hexString) {
		// hexString的长度对2取整，作为bytes的长度
		// int len = hexString.length()/2;
		// byte[] bytes = new byte[len];
		// byte high = 0;//字节高四位
		// byte low = 0;//字节低四位
		//
		// for(int i=0;i<len;i++){
		// //右移四位得到高位
		// high = (byte)((hexStr.indexOf(hexString.charAt(2*i)))<<4);
		// low = (byte)hexStr.indexOf(hexString.charAt(2*i+1));
		// bytes[i] = (byte) (high|low);//高地位做或运算
		// }
		// return bytes;

		// return new BigInteger(hexString,16).toByteArray();

		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	public static byte charToByte(char c) {
		return (byte) hexStr.indexOf(c);
	}
}
