/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.kernel.util;

/**
 * @author Brian Wing Shun Chan
 */
public class ArrayUtil_IW {
	public static ArrayUtil_IW getInstance() {
		return _instance;
	}

	public boolean[] append(boolean[]... arrays) {
		return ArrayUtil.append(arrays);
	}

	public boolean[] append(boolean[] array, boolean value) {
		return ArrayUtil.append(array, value);
	}

	public byte[] append(byte[]... arrays) {
		return ArrayUtil.append(arrays);
	}

	public byte[] append(byte[] array, byte value) {
		return ArrayUtil.append(array, value);
	}

	public char[] append(char[]... arrays) {
		return ArrayUtil.append(arrays);
	}

	public char[] append(char[] array, char value) {
		return ArrayUtil.append(array, value);
	}

	public double[] append(double[]... arrays) {
		return ArrayUtil.append(arrays);
	}

	public double[] append(double[] array, double value) {
		return ArrayUtil.append(array, value);
	}

	public float[] append(float[]... arrays) {
		return ArrayUtil.append(arrays);
	}

	public float[] append(float[] array, float value) {
		return ArrayUtil.append(array, value);
	}

	public int[] append(int[]... arrays) {
		return ArrayUtil.append(arrays);
	}

	public int[] append(int[] array, int value) {
		return ArrayUtil.append(array, value);
	}

	public long[] append(long[]... arrays) {
		return ArrayUtil.append(arrays);
	}

	public long[] append(long[] array, long value) {
		return ArrayUtil.append(array, value);
	}

	public short[] append(short[]... arrays) {
		return ArrayUtil.append(arrays);
	}

	public short[] append(short[] array, short value) {
		return ArrayUtil.append(array, value);
	}

	public <T> T[] append(T[]... arrays) {
		return ArrayUtil.append(arrays);
	}

	public <T> T[] append(T[] array, T value) {
		return ArrayUtil.append(array, value);
	}

	public <T> T[] append(T[] array1, T[] array2) {
		return ArrayUtil.append(array1, array2);
	}

	public <T> T[][] append(T[][] array1, T[] value) {
		return ArrayUtil.append(array1, value);
	}

	public <T> T[][] append(T[][] array1, T[][] array2) {
		return ArrayUtil.append(array1, array2);
	}

	public boolean[] clone(boolean[] array) {
		return ArrayUtil.clone(array);
	}

	public boolean[] clone(boolean[] array, int from, int to) {
		return ArrayUtil.clone(array, from, to);
	}

	public byte[] clone(byte[] array) {
		return ArrayUtil.clone(array);
	}

	public byte[] clone(byte[] array, int from, int to) {
		return ArrayUtil.clone(array, from, to);
	}

	public char[] clone(char[] array) {
		return ArrayUtil.clone(array);
	}

	public char[] clone(char[] array, int from, int to) {
		return ArrayUtil.clone(array, from, to);
	}

	public double[] clone(double[] array) {
		return ArrayUtil.clone(array);
	}

	public double[] clone(double[] array, int from, int to) {
		return ArrayUtil.clone(array, from, to);
	}

	public float[] clone(float[] array) {
		return ArrayUtil.clone(array);
	}

	public float[] clone(float[] array, int from, int to) {
		return ArrayUtil.clone(array, from, to);
	}

	public int[] clone(int[] array) {
		return ArrayUtil.clone(array);
	}

	public int[] clone(int[] array, int from, int to) {
		return ArrayUtil.clone(array, from, to);
	}

	public long[] clone(long[] array) {
		return ArrayUtil.clone(array);
	}

	public long[] clone(long[] array, int from, int to) {
		return ArrayUtil.clone(array, from, to);
	}

	public short[] clone(short[] array) {
		return ArrayUtil.clone(array);
	}

	public short[] clone(short[] array, int from, int to) {
		return ArrayUtil.clone(array, from, to);
	}

	public <T> T[] clone(T[] array) {
		return ArrayUtil.clone(array);
	}

	public <T> T[] clone(T[] array, int from, int to) {
		return ArrayUtil.clone(array, from, to);
	}

	public <T> T[][] clone(T[][] array) {
		return ArrayUtil.clone(array);
	}

	public <T> T[][] clone(T[][] array, int from, int to) {
		return ArrayUtil.clone(array, from, to);
	}

	public void combine(java.lang.Object[] array1, java.lang.Object[] array2,
		java.lang.Object[] combinedArray) {
		ArrayUtil.combine(array1, array2, combinedArray);
	}

	public boolean contains(boolean[] array, boolean value) {
		return ArrayUtil.contains(array, value);
	}

	public boolean contains(byte[] array, byte value) {
		return ArrayUtil.contains(array, value);
	}

	public boolean contains(char[] array, char value) {
		return ArrayUtil.contains(array, value);
	}

	public boolean contains(double[] array, double value) {
		return ArrayUtil.contains(array, value);
	}

	public boolean contains(float[] array, float value) {
		return ArrayUtil.contains(array, value);
	}

	public boolean contains(int[] array, int value) {
		return ArrayUtil.contains(array, value);
	}

	public boolean contains(long[] array, long value) {
		return ArrayUtil.contains(array, value);
	}

	public boolean contains(java.lang.Object[] array, java.lang.Object value) {
		return ArrayUtil.contains(array, value);
	}

	public boolean contains(short[] array, short value) {
		return ArrayUtil.contains(array, value);
	}

	public java.lang.String[] distinct(java.lang.String[] array) {
		return ArrayUtil.distinct(array);
	}

	public java.lang.String[] distinct(java.lang.String[] array,
		java.util.Comparator<java.lang.String> comparator) {
		return ArrayUtil.distinct(array, comparator);
	}

	public int getLength(java.lang.Object[] array) {
		return ArrayUtil.getLength(array);
	}

	public java.lang.Object getValue(java.lang.Object[] array, int pos) {
		return ArrayUtil.getValue(array, pos);
	}

	public boolean[] remove(boolean[] array, boolean value) {
		return ArrayUtil.remove(array, value);
	}

	public byte[] remove(byte[] array, byte value) {
		return ArrayUtil.remove(array, value);
	}

	public char[] remove(char[] array, char value) {
		return ArrayUtil.remove(array, value);
	}

	public double[] remove(double[] array, double value) {
		return ArrayUtil.remove(array, value);
	}

	public int[] remove(int[] array, int value) {
		return ArrayUtil.remove(array, value);
	}

	public long[] remove(long[] array, long value) {
		return ArrayUtil.remove(array, value);
	}

	public short[] remove(short[] array, short value) {
		return ArrayUtil.remove(array, value);
	}

	public java.lang.String[] remove(java.lang.String[] array,
		java.lang.String value) {
		return ArrayUtil.remove(array, value);
	}

	public java.lang.String[] removeByPrefix(java.lang.String[] array,
		java.lang.String prefix) {
		return ArrayUtil.removeByPrefix(array, prefix);
	}

	public void reverse(java.lang.String[] array) {
		ArrayUtil.reverse(array);
	}

	public boolean[] subset(boolean[] array, int start, int end) {
		return ArrayUtil.subset(array, start, end);
	}

	public byte[] subset(byte[] array, int start, int end) {
		return ArrayUtil.subset(array, start, end);
	}

	public char[] subset(char[] array, int start, int end) {
		return ArrayUtil.subset(array, start, end);
	}

	public double[] subset(double[] array, int start, int end) {
		return ArrayUtil.subset(array, start, end);
	}

	public float[] subset(float[] array, int start, int end) {
		return ArrayUtil.subset(array, start, end);
	}

	public int[] subset(int[] array, int start, int end) {
		return ArrayUtil.subset(array, start, end);
	}

	public long[] subset(long[] array, int start, int end) {
		return ArrayUtil.subset(array, start, end);
	}

	public short[] subset(short[] array, int start, int end) {
		return ArrayUtil.subset(array, start, end);
	}

	public <T> T[] subset(T[] array, int start, int end) {
		return ArrayUtil.subset(array, start, end);
	}

	public java.lang.Boolean[] toArray(boolean[] array) {
		return ArrayUtil.toArray(array);
	}

	public boolean[] toArray(java.lang.Boolean[] array) {
		return ArrayUtil.toArray(array);
	}

	public java.lang.Byte[] toArray(byte[] array) {
		return ArrayUtil.toArray(array);
	}

	public byte[] toArray(java.lang.Byte[] array) {
		return ArrayUtil.toArray(array);
	}

	public java.lang.Character[] toArray(char[] array) {
		return ArrayUtil.toArray(array);
	}

	public char[] toArray(java.lang.Character[] array) {
		return ArrayUtil.toArray(array);
	}

	public java.lang.Double[] toArray(double[] array) {
		return ArrayUtil.toArray(array);
	}

	public double[] toArray(java.lang.Double[] array) {
		return ArrayUtil.toArray(array);
	}

	public java.lang.Float[] toArray(float[] array) {
		return ArrayUtil.toArray(array);
	}

	public float[] toArray(java.lang.Float[] array) {
		return ArrayUtil.toArray(array);
	}

	public java.lang.Integer[] toArray(int[] array) {
		return ArrayUtil.toArray(array);
	}

	public int[] toArray(java.lang.Integer[] array) {
		return ArrayUtil.toArray(array);
	}

	public java.lang.Long[] toArray(long[] array) {
		return ArrayUtil.toArray(array);
	}

	public long[] toArray(java.lang.Long[] array) {
		return ArrayUtil.toArray(array);
	}

	public java.lang.Short[] toArray(short[] array) {
		return ArrayUtil.toArray(array);
	}

	public short[] toArray(java.lang.Short[] array) {
		return ArrayUtil.toArray(array);
	}

	public java.lang.Long[] toLongArray(java.lang.Object[] array) {
		return ArrayUtil.toLongArray(array);
	}

	public <T, V> java.lang.String toString(T[] list,
		com.liferay.portal.kernel.util.Accessor<T, V> accessor) {
		return ArrayUtil.toString(list, accessor);
	}

	public <T, V> java.lang.String toString(T[] list,
		com.liferay.portal.kernel.util.Accessor<T, V> accessor,
		java.lang.String delimiter) {
		return ArrayUtil.toString(list, accessor, delimiter);
	}

	public java.lang.String toString(java.lang.Object[] array,
		java.lang.String param) {
		return ArrayUtil.toString(array, param);
	}

	public java.lang.String toString(java.lang.Object[] array,
		java.lang.String param, java.lang.String delimiter) {
		return ArrayUtil.toString(array, param, delimiter);
	}

	public java.lang.String[] toStringArray(boolean[] array) {
		return ArrayUtil.toStringArray(array);
	}

	public java.lang.String[] toStringArray(byte[] array) {
		return ArrayUtil.toStringArray(array);
	}

	public java.lang.String[] toStringArray(char[] array) {
		return ArrayUtil.toStringArray(array);
	}

	public java.lang.String[] toStringArray(java.util.Date[] array,
		java.text.DateFormat dateFormat) {
		return ArrayUtil.toStringArray(array, dateFormat);
	}

	public java.lang.String[] toStringArray(double[] array) {
		return ArrayUtil.toStringArray(array);
	}

	public java.lang.String[] toStringArray(float[] array) {
		return ArrayUtil.toStringArray(array);
	}

	public java.lang.String[] toStringArray(int[] array) {
		return ArrayUtil.toStringArray(array);
	}

	public java.lang.String[] toStringArray(
		com.liferay.portal.kernel.json.JSONArray array) {
		return ArrayUtil.toStringArray(array);
	}

	public java.lang.String[] toStringArray(long[] array) {
		return ArrayUtil.toStringArray(array);
	}

	public java.lang.String[] toStringArray(java.lang.Object[] array) {
		return ArrayUtil.toStringArray(array);
	}

	public java.lang.String[] toStringArray(short[] array) {
		return ArrayUtil.toStringArray(array);
	}

	private ArrayUtil_IW() {
	}

	private static ArrayUtil_IW _instance = new ArrayUtil_IW();
}