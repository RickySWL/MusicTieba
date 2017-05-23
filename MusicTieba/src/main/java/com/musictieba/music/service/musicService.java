package com.musictieba.music.service;


import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;
import com.musictieba.pojo.Comment;
import com.musictieba.pojo.Music;
import com.musictieba.pojo.Note;
import com.musictieba.pojo.User;
import com.musictieba.util.PageBean;


public interface musicService {

	/**
	 * 
	* @Title: addMusic
	* @Description: 添加音乐
	* @param music 音乐实体类
	* @param musicUrl 存放音乐文件的地址
	* @param coverUrl 存放封面图片的纸质
	* @param u 用户实体类
	* @return
	* @throws Exception
	* @author: sunweilun
	* @date: 2017年5月21日
	 */
	Map<String, Object> addMusic(Music music, String musicUrl, String coverUrl, User u) throws Exception;

	/**
	 * 
	* @Title: saveCover
	* @Description:  保存封面图片
	* @param cover 存放图片的零食文件
	* @param pathUrl 存放地址
	* @return
	* @throws Exception
	* @author: sunweilun
	* @date: 2017年5月21日
	 */
	String saveCover(MultipartFile cover, String pathUrl) throws Exception;

	/**
	 * 
	* @Title: saveMp3
	* @Description:  保存音乐文件
	* @param mp3  临时音乐文件
	* @param pathUrl 存放地址
	* @return
	* @throws Exception
	* @author: sunweilun
	* @date: 2017年5月21日
	 */
	String saveMp3(MultipartFile mp3, String pathUrl) throws Exception;

	/**
	 * 
	* @Title: isMusic
	* @Description:  判断是否是音乐文件
	* @param Suffix 文件后缀
	* @return
	* @author: sunweilun
	* @date: 2017年5月21日
	 */
	boolean isMusic(String Suffix);

	/**
	 * 
	* @Title: createNote4Music
	* @Description: 为音乐创建一个主题帖子
	* @param u 当前用户是
	* @param music 音乐实体类
	* @return
	* @throws Exception
	* @author: sunweilun
	* @date: 2017年5月21日
	 */
	Note createNote4Music(User u, Music music) throws Exception;

	/**
	 * 
	* @Title: connect
	* @Description: 拼接帖子类容
	* @param m 音乐实体类
	* @return
	* @author: sunweilun
	* @date: 2017年5月21日
	 */
	String connect(Music m);

	/**
	 * 
	* @Title: countMusic
	* @Description: 统计音乐数量
	* @param searchkey 关键字
	* @return
	* @throws Exception
	* @author: sunweilun
	* @date: 2017年5月21日
	 */
	int countMusic(String searchkey) throws Exception;

	/**
	 * 
	* @Title: musicList
	* @Description: 音乐列表
	* @param searchkey 关键字
	* @param nowpageno 跳转页
	* @return
	* @throws Exception
	* @author: sunweilun
	* @date: 2017年5月21日
	 */
	List<Music> musicList(String searchkey, int nowpageno) throws Exception;

	/**
	 * 
	* @Title: commentList
	* @Description:  回帖列表
	* @param noteId 帖子id
	* @param nowpageno 跳转页
	* @return
	* @throws Exception
	* @author: sunweilun
	* @date: 2017年5月21日
	 */
	List<Comment> commentList(String noteId, int nowpageno) throws Exception;

	/**
	 * 
	* @Title: countComment
	* @Description: 统计回帖数量
	* @param noteid
	* @return
	* @throws Exception
	* @author: sunweilun
	* @date: 2017年5月21日
	 */
	int countComment(String noteid) throws Exception;

	/**
	 * 
	* @Title: qureyPageMusic
	* @Description:  分页查询音乐
	* @param newsk 关键字
	* @param topageno 跳转页
	* @return
	* @throws Exception
	* @author: sunweilun
	* @date: 2017年5月21日
	 */
	PageBean<Music> qureyPageMusic(String newsk, String topageno) throws Exception;

	/**
	 * 
	* @Title: qureyPageComment
	* @Description:  分页查询回帖
	* @param noteId
	* @param topageno
	* @return
	* @throws Exception
	* @author: sunweilun
	* @date: 2017年5月21日
	 */
	PageBean<Comment> qureyPageComment(String noteId, String topageno) throws Exception;

	/**
	 * 
	* @Title: qureyPageAllMusic
	* @Description: 
	* @param newsk 关键字
	* @param topageno 跳转页
	* @return
	* @throws Exception
	* @author: sunweilun
	* @date: 2017年5月21日
	 */
	PageBean<Music> qureyPageAllMusic(String newsk, String topageno) throws Exception;

	/**
	 * 
	* @Title: musicAllList
	* @Description: 所有音乐列表
	* @param key 关键字
	* @param nowPageNo 跳转页
	* @return
	* @author: sunweilun
	* @date: 2017年5月21日
	 */
	List<Music> musicAllList(String key, int nowPageNo);

	/**
	 * 
	* @Title: countAllMusic
	* @Description: 统计所有音乐的数量
	* @param newsk 关键字
	* @return
	* @author: sunweilun
	* @date: 2017年5月21日
	 */
	int countAllMusic(String newsk);

	/**
	 * 
	* @Title: changeMusicStatus
	* @Description: 改变音乐状态
	* @param mids 音乐id
	* @return
	* @author: sunweilun
	* @date: 2017年5月21日
	 */
	Map<String, Object> changeMusicStatus(String mids);

	/**
	 * 
	* @Title: findMuisc
	* @Description: 通过音乐id搜索音乐
	* @param muiscId 音乐id
	* @return
	* @author: sunweilun
	* @date: 2017年5月21日
	 */
	Music findMuisc(Integer muiscId);
}
