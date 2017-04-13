package com.swust.vhas.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.swust.vhas.model.Video;
import com.swust.vhas.service.VideoService;
import com.swust.vhas.view.JsonAndView;

@Controller
@RequestMapping(value = "/video")
public class VideoController {

	@Autowired
	VideoService videoService;

	@RequestMapping(value = "/top", method = RequestMethod.GET)
	@ResponseBody
	public JsonAndView top(String time, Integer size, String order, String type, Integer web) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		if ("day".equals(time)) {
			calendar.add(Calendar.DATE, -1);
		} else if ("week".equals(time)) {
			calendar.add(Calendar.WEEK_OF_YEAR, -1);
		} else if ("month".equals(time)) {
			calendar.add(Calendar.MONTH, -1);
		} else if ("year".equals(time)) {
			calendar.add(Calendar.YEAR, -1);
		} else {
			calendar = null;
		}
		if (calendar != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			time = sdf.format(calendar.getTime());
		}
		//
		if (size == null || size < 1)
			size = 10;
		if (order == null)
			order = "click";
		List<Video> videos = videoService.selectTop(time, size, order, type, web);
		JsonAndView jsonAndView = new JsonAndView();
		jsonAndView.addData("size", size);
		jsonAndView.addData("order", order);
		jsonAndView.addData("type", type);
		jsonAndView.addData("web", web);
		jsonAndView.addData("videos", videos);
		return jsonAndView;
	}

	@RequestMapping(value = "/type", method = RequestMethod.GET)
	@ResponseBody
	public JsonAndView type(Integer webId) {
		JsonAndView jsonAndView = new JsonAndView();
		jsonAndView.addData("types", videoService.selectAllTypes(webId));
		return jsonAndView;
	}

	@RequestMapping(value = "/typeup", method = RequestMethod.GET)
	@ResponseBody
	public JsonAndView typeup(String type) {
		JsonAndView jsonAndView = new JsonAndView();
		jsonAndView.addData("type", type);
		jsonAndView.addData("typeup", videoService.selectUpdateByType(type));
		return jsonAndView;
	}

	@RequestMapping(value = "/videoup", method = RequestMethod.GET)
	@ResponseBody
	public JsonAndView videoup(Integer webId, String vid) {
		JsonAndView jsonAndView = new JsonAndView();
		jsonAndView.addData("videos", videoService.selectUpdate(webId, vid));
		return jsonAndView;
	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	@ResponseBody
	public JsonAndView detail(Integer id) {
		if (id == null || id <= 0)
			return new JsonAndView(-1, "缺少参数");
		JsonAndView jsonAndView = new JsonAndView();
		jsonAndView.addData("video", videoService.selectByPrimaryKey(id));
		return jsonAndView;
	}

	/**
	 * 
	 * @author LiuJie
	 * @time 2016年6月30日 下午10:29:06
	 * @param id
	 *            根据作者id查找所有视频
	 * @return
	 */
	@RequestMapping(value = "/works", method = RequestMethod.GET)
	@ResponseBody
	public JsonAndView works(Integer id) {
		if (id == null || id <= 0)
			return new JsonAndView(-1, "缺少参数");
		JsonAndView jsonAndView = new JsonAndView();
		jsonAndView.addData("videos", videoService.selectAllByAuthorId(id));
		return jsonAndView;
	}
}
