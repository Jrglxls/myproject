package com.zhangjiajing.music.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zhangjiajing.music.model.ArtistInfo;

import java.util.ArrayList;
import java.util.List;

public class ArtistInfoDao {

	private static final String TABLE_ARTIST = "artist_info";
	private Context mContext;
	
	public ArtistInfoDao(Context context) {
		this.mContext = context;
	}

	/**
	 * 保存歌手信息
	 */
	public void saveArtistInfo(List<ArtistInfo> list) {
		SQLiteDatabase db = DatabaseHelper.getInstance(mContext);
		for (ArtistInfo info : list) {
			ContentValues cv = new ContentValues();
			cv.put("artist_name", info.artist_name);
			cv.put("number_of_tracks", info.number_of_tracks);
			db.insert(TABLE_ARTIST, null, cv);
		}
	}

	/**
	 * 获取歌手信息
	 */
	public List<ArtistInfo> getArtistInfo() {
		SQLiteDatabase db = DatabaseHelper.getInstance(mContext);
		List<ArtistInfo> list = new ArrayList<ArtistInfo>();
		String sql = "select * from " + TABLE_ARTIST;
		Cursor cursor = db.rawQuery(sql, null);
		while(cursor.moveToNext()) {
			ArtistInfo info = new ArtistInfo();
			info.artist_name = cursor.getString(cursor.getColumnIndex("artist_name"));
			info.number_of_tracks = cursor.getInt(cursor.getColumnIndex("number_of_tracks"));
			list.add(info);
		}
		cursor.close();
		return list;
	}
	
	/**
	 * 数据库中是否有数据
	 */
	public boolean hasData() {
		SQLiteDatabase db = DatabaseHelper.getInstance(mContext);
		String sql = "select count(*) from " + TABLE_ARTIST;
		Cursor cursor = db.rawQuery(sql, null);
		boolean has = false;
		if(cursor.moveToFirst()) {
			int count = cursor.getInt(0);
			if(count > 0) {
				has = true;
			}
		}
		cursor.close();
		return has;
	}

	/**
	 * 获取数据数
	 */
	public int getDataCount() {
		SQLiteDatabase db = DatabaseHelper.getInstance(mContext);
		String sql = "select count(*) from " + TABLE_ARTIST;
		Cursor cursor = db.rawQuery(sql, null);
		int count = 0;
		if(cursor.moveToFirst()) {
			count = cursor.getInt(0);
		}
		return count;
	}
}
