;(function($){

  var xSlider = function(el, userConfig) {

    var _this = this
    this.el = el

    // 参数配置
    this.userConfig = userConfig
    this.config = {
      w: this.el.width(),
      current: 0,
      speed: 500,
      intervalTime: 5000
    }
    if(userConfig != null) {
      $.extend(this.config,this.userConfig);
    }

    // 保存查找dom元素
    var slider_img = this.el.children('.slider-img')
    var slider_img_ul = slider_img.children('ul')
    var slider_img_ul_li = slider_img_ul.children('li')

    // 初始化左右按钮
    this.el.append('<a href="javascript:" class="slider-btn slider-btn-left">&lt;</a>')
    this.el.append('<a href="javascript:" class="slider-btn slider-btn-right">&gt;</a>')
    var slider_btn_left = this.el.children('.slider-btn-left')
    var slider_btn_right = this.el.children('.slider-btn-right')

    // 初始化圆点
    this.el.append('<div class="slider-dot"><ul></ul></div>')
    var slider_dot = this.el.children('.slider-dot')
    var slider_dot_ul = slider_dot.children('ul')
    var slider_img_length = slider_img_ul_li.length
    for (var i = 0; i < slider_img_length - 2; i++) {
      if(i === this.config.current){
        slider_dot_ul.append('<li class="active"></li>')
      } else {
        slider_dot_ul.append('<li></li>')
      }
    }
    var slider_dot_ul_li = slider_dot_ul.children('li')

    // 初始化默认显示图片位置
    slider_img_ul.css('left', - this.config.w * this.config.current - this.config.w)

    // 圆点切换点亮
    var active = function(i) {
      slider_dot_ul_li.removeClass('active')
      slider_dot_ul_li.eq(i).addClass('active')
    }

    // 右点击事件
    slider_btn_right.on('click', function(event) {
      event.preventDefault()
      if(_this.config.current < slider_img_length - 2){
        toggleInterval ()
        _this.config.current ++
        if(_this.config.current != slider_img_length - 2) {
          slider_img_ul.stop(true, false).animate({left: - _this.config.w * _this.config.current - _this.config.w}, _this.config.speed, function () {
            active(_this.config.current)
          })
        }
        if (_this.config.current === slider_img_length - 2) {
          slider_img_ul.stop(true, false).animate({left: - _this.config.w * _this.config.current - _this.config.w}, _this.config.speed, function() {
            slider_img_ul.css('left', - _this.config.w)
            _this.config.current = 0
            active(_this.config.current)
          })
        }
      }
    })

    // 左点击事件
    slider_btn_left.on('click', function(event) {
      event.preventDefault()
      if(_this.config.current > -1){
        toggleInterval ()
        _this.config.current --
        if(_this.config.current != -1){
          slider_img_ul.stop(true, false).animate({left: - _this.config.w * _this.config.current - _this.config.w}, _this.config.speed, function() {
            active(_this.config.current)
          })
        }
        if(_this.config.current === -1){
          slider_img_ul.stop(true, false).animate({left: 0}, _this.config.speed, function() {
            slider_img_ul.css('left', - _this.config.w * (slider_img_length - 2))
            _this.config.current = slider_img_length - 3
            active(_this.config.current)
          })
        }
      }
    })

    // dot点击事件
    slider_dot_ul_li.on('click', function(event) {
      event.preventDefault()
      toggleInterval ()
      var index = $(this).index()
      active(index)
      slider_img_ul.stop(true, false).animate({left: - _this.config.w * index - _this.config.w}, _this.config.speed, function() {
        _this.config.current = index
      })
    })

    // 自动切换
    var sliderInt = setInterval(sliderInterval, _this.config.intervalTime)
    // 判断图片切换
    function sliderInterval() {
      if (_this.config.current < slider_img_length - 2) {
        _this.config.current ++
        slider_img_ul.stop(true, false).animate({left: - _this.config.w * _this.config.current - _this.config.w}, _this.config.speed, function() {
          active(_this.config.current)
          if (_this.config.current === slider_img_length - 2) {
            slider_img_ul.css('left', - _this.config.w)
            _this.config.current = 0
            active(_this.config.current)
          }
        })
      }
    }
    // 重置计时器
    function toggleInterval () {
      clearInterval(sliderInt)
      sliderInt = setInterval(sliderInterval, _this.config.intervalTime)
    }

  } // --end-- xSlider

  $.fn.extend({
    xSlider: function() {
      new xSlider($(this))
    }
  })

})(jQuery)

var config = {
  current: 0,
  speed: 500,
  intervalTime: 2000
}
$('.slider').xSlider(config)
