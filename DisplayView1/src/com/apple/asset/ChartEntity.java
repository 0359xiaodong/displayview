package com.apple.asset;

import java.util.HashMap;
import java.util.List;

/**
 * ��״ͼ��ʵ����
 * 
 * @author ����ƽ
 * 
 */
public class ChartEntity {
	// ÿ����������������ʾ����String = ��ʾ����
	public List<String> hList;
	// ÿ�����κ���������ʾ����String = ��ʾ����,true|false
	public List<String> wList;
	//������ʾֵ
	public HashMap<String,Integer> map;
	//���α�����
	public int scale;
	// ÿ�����κ�����״��ʾ���
	public int row_weight = 0;
	// ÿ�����κ�����״���
	public int padding_weight = 0;
	//������ʼ��x����
	public int startX = 65;
	//ÿ������������ʾ���
	public int row_height=0;
	//ÿ����������
	public int padding_height=40;
	public ChartEntity() {
		
		// TODO Auto-generated constructor stub
	}
}
