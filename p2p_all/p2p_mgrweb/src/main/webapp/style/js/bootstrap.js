/* ===================================================
 * bootstrap-transition.js v2.2.1
 * http://twitter.github.com/bootstrap/javascript.html#transitions
 * ===================================================
 * Copyright 2012 Twitter, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ========================================================== */


!function ($) {

  "use strict"; // jshint ;_;


  /* CSS TRANSITION SUPPORT (http://www.modernizr.com/)
   * ======================================================= */

  $(function () {

    $.support.transition = (function () {

      var transitionEnd = (function () {

        var el = document.createElement('bootstrap')
          , transEndEventNames = {
               'WebkitTransition' : 'webkitTransitionEnd'
            ,  'MozTransition'    : 'transitionend'
            ,  'OTransition'      : 'oTransitionEnd otransitionend'
            ,  'transition'       : 'transitionend'
            }
          , name

        for (name in transEndEventNames){
          if (el.style[name] !== undefined) {
            return transEndEventNames[name]
          }
        }

      }())

      return transitionEnd && {
        end: transitionEnd
      }

    })()

  })

}(window.jQuery);
/* =========================================================
 * bootstrap-modal.js v2.2.1
 * http://twitter.github.com/bootstrap/javascript.html#modals
 * =========================================================
 * Copyright 2012 Twitter, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ========================================================= */


!function ($) {

  "use strict"; // jshint ;_;


 /* MODAL CLASS DEFINITION
  * ====================== */

  var Modal = function (element, options) {
    this.options = options
    this.$element = $(element)
      .delegate('[data-dismiss="modal"]', 'click.dismiss.modal', $.proxy(this.hide, this))
    this.options.remote && this.$element.find('.modal-body').load(this.options.remote)
  }

  Modal.prototype = {

      constructor: Modal

    , toggle: function () {
        return this[!this.isShown ? 'show' : 'hide']()
      }

    , show: function () {
        var that = this
          , e = $.Event('show')

        this.$element.trigger(e)

        if (this.isShown || e.isDefaultPrevented()) return

        this.isShown = true

        this.escape()

        this.backdrop(function () {
          var transition = $.support.transition && that.$element.hasClass('fade')

          if (!that.$element.parent().length) {
            that.$element.appendTo(document.body) //don't move modals dom position
          }

          that.$element
            .show()

          if (transition) {
            that.$element[0].offsetWidth // force reflow
          }

          that.$element
            .addClass('in')
            .attr('aria-hidden', false)

          that.enforceFocus()

          transition ?
            that.$element.one($.support.transition.end, function () { that.$element.focus().trigger('shown') }) :
            that.$element.focus().trigger('shown')

        })
      }

    , hide: function (e) {
        e && e.preventDefault()

        var that = this

        e = $.Event('hide')

        this.$element.trigger(e)

        if (!this.isShown || e.isDefaultPrevented()) return

        this.isShown = false

        this.escape()

        $(document).off('focusin.modal')

        this.$element
          .removeClass('in')
          .attr('aria-hidden', true)

        $.support.transition && this.$element.hasClass('fade') ?
          this.hideWithTransition() :
          this.hideModal()
      }

    , enforceFocus: function () {
        var that = this
        $(document).on('focusin.modal', function (e) {
          if (that.$element[0] !== e.target && !that.$element.has(e.target).length) {
            that.$element.focus()
          }
        })
      }

    , escape: function () {
        var that = this
        if (this.isShown && this.options.keyboard) {
          this.$element.on('keyup.dismiss.modal', function ( e ) {
            e.which == 27 && that.hide()
          })
        } else if (!this.isShown) {
          this.$element.off('keyup.dismiss.modal')
        }
      }

    , hideWithTransition: function () {
        var that = this
          , timeout = setTimeout(function () {
              that.$element.off($.support.transition.end)
              that.hideModal()
            }, 500)

        this.$element.one($.support.transition.end, function () {
          clearTimeout(timeout)
          that.hideModal()
        })
      }

    , hideModal: function (that) {
        this.$element
          .hide()
          .trigger('hidden')

        this.backdrop()
      }

    , removeBackdrop: function () {
        this.$backdrop.remove()
        this.$backdrop = null
      }

    , backdrop: function (callback) {
        var that = this
          , animate = this.$element.hasClass('fade') ? 'fade' : ''

        if (this.isShown && this.options.backdrop) {
          var doAnimate = $.support.transition && animate

          this.$backdrop = $('<div class="modal-backdrop ' + animate + '" />')
            .appendTo(document.body)

          this.$backdrop.click(
            this.options.backdrop == 'static' ?
              $.proxy(this.$element[0].focus, this.$element[0])
            : $.proxy(this.hide, this)
          )

          if (doAnimate) this.$backdrop[0].offsetWidth // force reflow

          this.$backdrop.addClass('in')

          doAnimate ?
            this.$backdrop.one($.support.transition.end, callback) :
            callback()

        } else if (!this.isShown && this.$backdrop) {
          this.$backdrop.removeClass('in')

          $.support.transition && this.$element.hasClass('fade')?
            this.$backdrop.one($.support.transition.end, $.proxy(this.removeBackdrop, this)) :
            this.removeBackdrop()

        } else if (callback) {
          callback()
        }
      }
  }


 /* MODAL PLUGIN DEFINITION
  * ======================= */

  $.fn.modal = function (option) {
    return this.each(function () {
      var $this = $(this)
        , data = $this.data('modal')
        , options = $.extend({}, $.fn.modal.defaults, $this.data(), typeof option == 'object' && option)
      if (!data) $this.data('modal', (data = new Modal(this, options)))
      if (typeof option == 'string') data[option]()
      else if (options.show) data.show()
    })
  }

  $.fn.modal.defaults = {
      backdrop: true
    , keyboard: true
    , show: true
  }

  $.fn.modal.Constructor = Modal


 /* MODAL DATA-API
  * ============== */

  $(document).on('click.modal.data-api', '[data-toggle="modal"]', function (e) {
    var $this = $(this)
      , href = $this.attr('href')
      , $target = $($this.attr('data-target') || (href && href.replace(/.*(?=#[^\s]+$)/, ''))) //strip for ie7
      , option = $target.data('modal') ? 'toggle' : $.extend({ remote:!/#/.test(href) && href }, $target.data(), $this.data())

    e.preventDefault()

    $target
      .modal(option)
      .one('hide', function () {
        $this.focus()
      })
  })

}(window.jQuery);

/* ============================================================
 * bootstrap-dropdown.js v2.2.1
 * http://twitter.github.com/bootstrap/javascript.html#dropdowns
 * ============================================================
 * Copyright 2012 Twitter, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ============================================================ */


!function ($) {

  "use strict"; // jshint ;_;


 /* DROPDOWN CLASS DEFINITION
  * ========================= */

  var toggle = '[data-toggle=dropdown]'
    , Dropdown = function (element) {
        var $el = $(element).on('click.dropdown.data-api', this.toggle)
        $('html').on('click.dropdown.data-api', function () {
          $el.parent().removeClass('open')
        })
      }

  Dropdown.prototype = {

    constructor: Dropdown

  , toggle: function (e) {
      var $this = $(this)
        , $parent
        , isActive

      if ($this.is('.disabled, :disabled')) return

      $parent = getParent($this)

      isActive = $parent.hasClass('open')

      clearMenus()

      if (!isActive) {
        $parent.toggleClass('open')
        $this.focus()
      }

      return false
    }

  , keydown: function (e) {
      var $this
        , $items
        , $active
        , $parent
        , isActive
        , index

      if (!/(38|40|27)/.test(e.keyCode)) return

      $this = $(this)

      e.preventDefault()
      e.stopPropagation()

      if ($this.is('.disabled, :disabled')) return

      $parent = getParent($this)

      isActive = $parent.hasClass('open')

      if (!isActive || (isActive && e.keyCode == 27)) return $this.click()

      $items = $('[role=menu] li:not(.divider) a', $parent)

      if (!$items.length) return

      index = $items.index($items.filter(':focus'))

      if (e.keyCode == 38 && index > 0) index--                                        // up
      if (e.keyCode == 40 && index < $items.length - 1) index++                        // down
      if (!~index) index = 0

      $items
        .eq(index)
        .focus()
    }

  }

  function clearMenus() {
    $(toggle).each(function () {
      getParent($(this)).removeClass('open')
    })
  }

  function getParent($this) {
    var selector = $this.attr('data-target')
      , $parent

    if (!selector) {
      selector = $this.attr('href')
      selector = selector && /#/.test(selector) && selector.replace(/.*(?=#[^\s]*$)/, '') //strip for ie7
    }

    $parent = $(selector)
    $parent.length || ($parent = $this.parent())

    return $parent
  }


  /* DROPDOWN PLUGIN DEFINITION
   * ========================== */

  $.fn.dropdown = function (option) {
    return this.each(function () {
      var $this = $(this)
        , data = $this.data('dropdown')
      if (!data) $this.data('dropdown', (data = new Dropdown(this)))
      if (typeof option == 'string') data[option].call($this)
    })
  }

  $.fn.dropdown.Constructor = Dropdown


  /* APPLY TO STANDARD DROPDOWN ELEMENTS
   * =================================== */

  $(document)
    .on('click.dropdown.data-api touchstart.dropdown.data-api', clearMenus)
    .on('click.dropdown touchstart.dropdown.data-api', '.dropdown form', function (e) { e.stopPropagation() })
    .on('click.dropdown.data-api touchstart.dropdown.data-api'  , toggle, Dropdown.prototype.toggle)
    .on('keydown.dropdown.data-api touchstart.dropdown.data-api', toggle + ', [role=menu]' , Dropdown.prototype.keydown)

}(window.jQuery);
/* =============================================================
 * bootstrap-scrollspy.js v2.2.1
 * http://twitter.github.com/bootstrap/javascript.html#scrollspy
 * =============================================================
 * Copyright 2012 Twitter, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ============================================================== */


!function ($) {

  "use strict"; // jshint ;_;


 /* SCROLLSPY CLASS DEFINITION
  * ========================== */

  function ScrollSpy(element, options) {
    var process = $.proxy(this.process, this)
      , $element = $(element).is('body') ? $(window) : $(element)
      , href
    this.options = $.extend({}, $.fn.scrollspy.defaults, options)
    this.$scrollElement = $element.on('scroll.scroll-spy.data-api', process)
    this.selector = (this.options.target
      || ((href = $(element).attr('href')) && href.replace(/.*(?=#[^\s]+$)/, '')) //strip for ie7
      || '') + ' .nav li > a'
    this.$body = $('body')
    this.refresh()
    this.process()
  }

  ScrollSpy.prototype = {

      constructor: ScrollSpy

    , refresh: function () {
        var self = this
          , $targets

        this.offsets = $([])
        this.targets = $([])

        $targets = this.$body
          .find(this.selector)
          .map(function () {
            var $el = $(this)
              , href = $el.data('target') || $el.attr('href')
              , $href = /^#\w/.test(href) && $(href)
            return ( $href
              && $href.length
              && [[ $href.position().top, href ]] ) || null
          })
          .sort(function (a, b) { return a[0] - b[0] })
          .each(function () {
            self.offsets.push(this[0])
            self.targets.push(this[1])
          })
      }

    , process: function () {
        var scrollTop = this.$scrollElement.scrollTop() + this.options.offset
          , scrollHeight = this.$scrollElement[0].scrollHeight || this.$body[0].scrollHeight
          , maxScroll = scrollHeight - this.$scrollElement.height()
          , offsets = this.offsets
          , targets = this.targets
          , activeTarget = this.activeTarget
          , i

        if (scrollTop >= maxScroll) {
          return activeTarget != (i = targets.last()[0])
            && this.activate ( i )
        }

        for (i = offsets.length; i--;) {
          activeTarget != targets[i]
            && scrollTop >= offsets[i]
            && (!offsets[i + 1] || scrollTop <= offsets[i + 1])
            && this.activate( targets[i] )
        }
      }

    , activate: function (target) {
        var active
          , selector

        this.activeTarget = target

        $(this.selector)
          .parent('.active')
          .removeClass('active')

        selector = this.selector
          + '[data-target="' + target + '"],'
          + this.selector + '[href="' + target + '"]'

        active = $(selector)
          .parent('li')
          .addClass('active')

        if (active.parent('.dropdown-menu').length)  {
          active = active.closest('li.dropdown').addClass('active')
        }

        active.trigger('activate')
      }

  }


 /* SCROLLSPY PLUGIN DEFINITION
  * =========================== */

  $.fn.scrollspy = function (option) {
    return this.each(function () {
      var $this = $(this)
        , data = $this.data('scrollspy')
        , options = typeof option == 'object' && option
      if (!data) $this.data('scrollspy', (data = new ScrollSpy(this, options)))
      if (typeof option == 'string') data[option]()
    })
  }

  $.fn.scrollspy.Constructor = ScrollSpy

  $.fn.scrollspy.defaults = {
    offset: 10
  }


 /* SCROLLSPY DATA-API
  * ================== */

  $(window).on('load', function () {
    $('[data-spy="scroll"]').each(function () {
      var $spy = $(this)
      $spy.scrollspy($spy.data())
    })
  })

}(window.jQuery);
/* ========================================================
 * bootstrap-tab.js v2.2.1
 * http://twitter.github.com/bootstrap/javascript.html#tabs
 * ========================================================
 * Copyright 2012 Twitter, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ======================================================== */


!function ($) {

  "use strict"; // jshint ;_;


 /* TAB CLASS DEFINITION
  * ==================== */

  var Tab = function (element) {
    this.element = $(element)
  }

  Tab.prototype = {

    constructor: Tab

  , show: function () {
      var $this = this.element
        , $ul = $this.closest('ul:not(.dropdown-menu)')
        , selector = $this.attr('data-target')
        , previous
        , $target
        , e

      if (!selector) {
        selector = $this.attr('href')
        selector = selector && selector.replace(/.*(?=#[^\s]*$)/, '') //strip for ie7
      }

      if ( $this.parent('li').hasClass('active') ) return

      previous = $ul.find('.active:last a')[0]

      e = $.Event('show', {
        relatedTarget: previous
      })

      $this.trigger(e)

      if (e.isDefaultPrevented()) return

      $target = $(selector)

      this.activate($this.parent('li'), $ul)
      this.activate($target, $target.parent(), function () {
        $this.trigger({
          type: 'shown'
        , relatedTarget: previous
        })
      })
    }

  , activate: function ( element, container, callback) {
      var $active = container.find('> .active')
        , transition = callback
            && $.support.transition
            && $active.hasClass('fade')

      function next() {
        $active
          .removeClass('active')
          .find('> .dropdown-menu > .active')
          .removeClass('active')

        element.addClass('active')

        if (transition) {
          element[0].offsetWidth // reflow for transition
          element.addClass('in')
        } else {
          element.removeClass('fade')
        }

        if ( element.parent('.dropdown-menu') ) {
          element.closest('li.dropdown').addClass('active')
        }

        callback && callback()
      }

      transition ?
        $active.one($.support.transition.end, next) :
        next()

      $active.removeClass('in')
    }
  }


 /* TAB PLUGIN DEFINITION
  * ===================== */

  $.fn.tab = function ( option ) {
    return this.each(function () {
      var $this = $(this)
        , data = $this.data('tab')
      if (!data) $this.data('tab', (data = new Tab(this)))
      if (typeof option == 'string') data[option]()
    })
  }

  $.fn.tab.Constructor = Tab


 /* TAB DATA-API
  * ============ */

  $(document).on('click.tab.data-api', '[data-toggle="tab"], [data-toggle="pill"]', function (e) {
    e.preventDefault()
    $(this).tab('show')
  })

}(window.jQuery);
/* ===========================================================
 * bootstrap-tooltip.js v2.2.1
 * http://twitter.github.com/bootstrap/javascript.html#tooltips
 * Inspired by the original jQuery.tipsy by Jason Frame
 * ===========================================================
 * Copyright 2012 Twitter, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ========================================================== */


!function ($) {

  "use strict"; // jshint ;_;


 /* TOOLTIP PUBLIC CLASS DEFINITION
  * =============================== */

  var Tooltip = function (element, options) {
    this.init('tooltip', element, options)
  }

  Tooltip.prototype = {

    constructor: Tooltip

  , init: function (type, element, options) {
      var eventIn
        , eventOut

      this.type = type
      this.$element = $(element)
      this.options = this.getOptions(options)
      this.enabled = true

      if (this.options.trigger == 'click') {
        this.$element.on('click.' + this.type, this.options.selector, $.proxy(this.toggle, this))
      } else if (this.options.trigger != 'manual') {
        eventIn = this.options.trigger == 'hover' ? 'mouseenter' : 'focus'
        eventOut = this.options.trigger == 'hover' ? 'mouseleave' : 'blur'
        this.$element.on(eventIn + '.' + this.type, this.options.selector, $.proxy(this.enter, this))
        this.$element.on(eventOut + '.' + this.type, this.options.selector, $.proxy(this.leave, this))
      }

      this.options.selector ?
        (this._options = $.extend({}, this.options, { trigger: 'manual', selector: '' })) :
        this.fixTitle()
    }

  , getOptions: function (options) {
      options = $.extend({}, $.fn[this.type].defaults, options, this.$element.data())

      if (options.delay && typeof options.delay == 'number') {
        options.delay = {
          show: options.delay
        , hide: options.delay
        }
      }

      return options
    }

  , enter: function (e) {
      var self = $(e.currentTarget)[this.type](this._options).data(this.type)

      if (!self.options.delay || !self.options.delay.show) return self.show()

      clearTimeout(this.timeout)
      self.hoverState = 'in'
      this.timeout = setTimeout(function() {
        if (self.hoverState == 'in') self.show()
      }, self.options.delay.show)
    }

  , leave: function (e) {
      var self = $(e.currentTarget)[this.type](this._options).data(this.type)

      if (this.timeout) clearTimeout(this.timeout)
      if (!self.options.delay || !self.options.delay.hide) return self.hide()

      self.hoverState = 'out'
      this.timeout = setTimeout(function() {
        if (self.hoverState == 'out') self.hide()
      }, self.options.delay.hide)
    }

  , show: function () {
      var $tip
        , inside
        , pos
        , actualWidth
        , actualHeight
        , placement
        , tp

      if (this.hasContent() && this.enabled) {
        $tip = this.tip()
        this.setContent()

        if (this.options.animation) {
          $tip.addClass('fade')
        }

        placement = typeof this.options.placement == 'function' ?
          this.options.placement.call(this, $tip[0], this.$element[0]) :
          this.options.placement

        inside = /in/.test(placement)

        $tip
          .detach()
          .css({ top: 0, left: 0, display: 'block' })
          .insertAfter(this.$element)

        pos = this.getPosition(inside)

        actualWidth = $tip[0].offsetWidth
        actualHeight = $tip[0].offsetHeight

        switch (inside ? placement.split(' ')[1] : placement) {
          case 'bottom':
            tp = {top: pos.top + pos.height, left: pos.left + pos.width / 2 - actualWidth / 2}
            break
          case 'top':
            tp = {top: pos.top - actualHeight, left: pos.left + pos.width / 2 - actualWidth / 2}
            break
          case 'left':
            tp = {top: pos.top + pos.height / 2 - actualHeight / 2, left: pos.left - actualWidth}
            break
          case 'right':
            tp = {top: pos.top + pos.height / 2 - actualHeight / 2, left: pos.left + pos.width}
            break
        }

        $tip
          .offset(tp)
          .addClass(placement)
          .addClass('in')
      }
    }

  , setContent: function () {
      var $tip = this.tip()
        , title = this.getTitle()

      $tip.find('.tooltip-inner')[this.options.html ? 'html' : 'text'](title)
      $tip.removeClass('fade in top bottom left right')
    }

  , hide: function () {
      var that = this
        , $tip = this.tip()

      $tip.removeClass('in')

      function removeWithAnimation() {
        var timeout = setTimeout(function () {
          $tip.off($.support.transition.end).detach()
        }, 500)

        $tip.one($.support.transition.end, function () {
          clearTimeout(timeout)
          $tip.detach()
        })
      }

      $.support.transition && this.$tip.hasClass('fade') ?
        removeWithAnimation() :
        $tip.detach()

      return this
    }

  , fixTitle: function () {
      var $e = this.$element
      if ($e.attr('title') || typeof($e.attr('data-original-title')) != 'string') {
        $e.attr('data-original-title', $e.attr('title') || '').removeAttr('title')
      }
    }

  , hasContent: function () {
      return this.getTitle()
    }

  , getPosition: function (inside) {
      return $.extend({}, (inside ? {top: 0, left: 0} : this.$element.offset()), {
        width: this.$element[0].offsetWidth
      , height: this.$element[0].offsetHeight
      })
    }

  , getTitle: function () {
      var title
        , $e = this.$element
        , o = this.options

      title = $e.attr('data-original-title')
        || (typeof o.title == 'function' ? o.title.call($e[0]) :  o.title)

      return title
    }

  , tip: function () {
      return this.$tip = this.$tip || $(this.options.template)
    }

  , validate: function () {
      if (!this.$element[0].parentNode) {
        this.hide()
        this.$element = null
        this.options = null
      }
    }

  , enable: function () {
      this.enabled = true
    }

  , disable: function () {
      this.enabled = false
    }

  , toggleEnabled: function () {
      this.enabled = !this.enabled
    }

  , toggle: function (e) {
      var self = $(e.currentTarget)[this.type](this._options).data(this.type)
      self[self.tip().hasClass('in') ? 'hide' : 'show']()
    }

  , destroy: function () {
      this.hide().$element.off('.' + this.type).removeData(this.type)
    }

  }


 /* TOOLTIP PLUGIN DEFINITION
  * ========================= */

  $.fn.tooltip = function ( option ) {
    return this.each(function () {
      var $this = $(this)
        , data = $this.data('tooltip')
        , options = typeof option == 'object' && option
      if (!data) $this.data('tooltip', (data = new Tooltip(this, options)))
      if (typeof option == 'string') data[option]()
    })
  }

  $.fn.tooltip.Constructor = Tooltip

  $.fn.tooltip.defaults = {
    animation: true
  , placement: 'top'
  , selector: false
  , template: '<div class="tooltip"><div class="tooltip-arrow"></div><div class="tooltip-inner"></div></div>'
  , trigger: 'hover'
  , title: ''
  , delay: 0
  , html: false
  }

}(window.jQuery);
/* ===========================================================
 * bootstrap-popover.js v2.2.1
 * http://twitter.github.com/bootstrap/javascript.html#popovers
 * ===========================================================
 * Copyright 2012 Twitter, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =========================================================== */


!function ($) {

  "use strict"; // jshint ;_;


 /* POPOVER PUBLIC CLASS DEFINITION
  * =============================== */

  var Popover = function (element, options) {
    this.init('popover', element, options)
  }


  /* NOTE: POPOVER EXTENDS BOOTSTRAP-TOOLTIP.js
     ========================================== */

  Popover.prototype = $.extend({}, $.fn.tooltip.Constructor.prototype, {

    constructor: Popover

  , setContent: function () {
      var $tip = this.tip()
        , title = this.getTitle()
        , content = this.getContent()

      $tip.find('.popover-title')[this.options.html ? 'html' : 'text'](title)
      $tip.find('.popover-content > *')[this.options.html ? 'html' : 'text'](content)

      $tip.removeClass('fade top bottom left right in')
    }

  , hasContent: function () {
      return this.getTitle() || this.getContent()
    }

  , getContent: function () {
      var content
        , $e = this.$element
        , o = this.options

      content = $e.attr('data-content')
        || (typeof o.content == 'function' ? o.content.call($e[0]) :  o.content)

      return content
    }

  , tip: function () {
      if (!this.$tip) {
        this.$tip = $(this.options.template)
      }
      return this.$tip
    }

  , destroy: function () {
      this.hide().$element.off('.' + this.type).removeData(this.type)
    }

  })


 /* POPOVER PLUGIN DEFINITION
  * ======================= */

  $.fn.popover = function (option) {
    return this.each(function () {
      var $this = $(this)
        , data = $this.data('popover')
        , options = typeof option == 'object' && option
      if (!data) $this.data('popover', (data = new Popover(this, options)))
      if (typeof option == 'string') data[option]()
    })
  }

  $.fn.popover.Constructor = Popover

  $.fn.popover.defaults = $.extend({} , $.fn.tooltip.defaults, {
    placement: 'right'
  , trigger: 'click'
  , content: ''
  , template: '<div class="popover"><div class="arrow"></div><div class="popover-inner"><h3 class="popover-title"></h3><div class="popover-content"><p></p></div></div></div>'
  })

}(window.jQuery);
/* ==========================================================
 * bootstrap-affix.js v2.2.1
 * http://twitter.github.com/bootstrap/javascript.html#affix
 * ==========================================================
 * Copyright 2012 Twitter, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ========================================================== */


!function ($) {

  "use strict"; // jshint ;_;


 /* AFFIX CLASS DEFINITION
  * ====================== */

  var Affix = function (element, options) {
    this.options = $.extend({}, $.fn.affix.defaults, options)
    this.$window = $(window)
      .on('scroll.affix.data-api', $.proxy(this.checkPosition, this))
      .on('click.affix.data-api',  $.proxy(function () { setTimeout($.proxy(this.checkPosition, this), 1) }, this))
    this.$element = $(element)
    this.checkPosition()
  }

  Affix.prototype.checkPosition = function () {
    if (!this.$element.is(':visible')) return

    var scrollHeight = $(document).height()
      , scrollTop = this.$window.scrollTop()
      , position = this.$element.offset()
      , offset = this.options.offset
      , offsetBottom = offset.bottom
      , offsetTop = offset.top
      , reset = 'affix affix-top affix-bottom'
      , affix

    if (typeof offset != 'object') offsetBottom = offsetTop = offset
    if (typeof offsetTop == 'function') offsetTop = offset.top()
    if (typeof offsetBottom == 'function') offsetBottom = offset.bottom()

    affix = this.unpin != null && (scrollTop + this.unpin <= position.top) ?
      false    : offsetBottom != null && (position.top + this.$element.height() >= scrollHeight - offsetBottom) ?
      'bottom' : offsetTop != null && scrollTop <= offsetTop ?
      'top'    : false

    if (this.affixed === affix) return

    this.affixed = affix
    this.unpin = affix == 'bottom' ? position.top - scrollTop : null

    this.$element.removeClass(reset).addClass('affix' + (affix ? '-' + affix : ''))
  }


 /* AFFIX PLUGIN DEFINITION
  * ======================= */

  $.fn.affix = function (option) {
    return this.each(function () {
      var $this = $(this)
        , data = $this.data('affix')
        , options = typeof option == 'object' && option
      if (!data) $this.data('affix', (data = new Affix(this, options)))
      if (typeof option == 'string') data[option]()
    })
  }

  $.fn.affix.Constructor = Affix

  $.fn.affix.defaults = {
    offset: 0
  }


 /* AFFIX DATA-API
  * ============== */

  $(window).on('load', function () {
    $('[data-spy="affix"]').each(function () {
      var $spy = $(this)
        , data = $spy.data()

      data.offset = data.offset || {}

      data.offsetBottom && (data.offset.bottom = data.offsetBottom)
      data.offsetTop && (data.offset.top = data.offsetTop)

      $spy.affix(data)
    })
  })


}(window.jQuery);
/* ==========================================================
 * bootstrap-alert.js v2.2.1
 * http://twitter.github.com/bootstrap/javascript.html#alerts
 * ==========================================================
 * Copyright 2012 Twitter, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ========================================================== */


!function ($) {

  "use strict"; // jshint ;_;


 /* ALERT CLASS DEFINITION
  * ====================== */

  var dismiss = '[data-dismiss="alert"]'
    , Alert = function (el) {
        $(el).on('click', dismiss, this.close)
      }

  Alert.prototype.close = function (e) {
    var $this = $(this)
      , selector = $this.attr('data-target')
      , $parent

    if (!selector) {
      selector = $this.attr('href')
      selector = selector && selector.replace(/.*(?=#[^\s]*$)/, '') //strip for ie7
    }

    $parent = $(selector)

    e && e.preventDefault()

    $parent.length || ($parent = $this.hasClass('alert') ? $this : $this.parent())

    $parent.trigger(e = $.Event('close'))

    if (e.isDefaultPrevented()) return

    $parent.removeClass('in')

    function removeElement() {
      $parent
        .trigger('closed')
        .remove()
    }

    $.support.transition && $parent.hasClass('fade') ?
      $parent.on($.support.transition.end, removeElement) :
      removeElement()
  }


 /* ALERT PLUGIN DEFINITION
  * ======================= */

  $.fn.alert = function (option) {
    return this.each(function () {
      var $this = $(this)
        , data = $this.data('alert')
      if (!data) $this.data('alert', (data = new Alert(this)))
      if (typeof option == 'string') data[option].call($this)
    })
  }

  $.fn.alert.Constructor = Alert


 /* ALERT DATA-API
  * ============== */

  $(document).on('click.alert.data-api', dismiss, Alert.prototype.close)

}(window.jQuery);
/* ============================================================
 * bootstrap-button.js v2.2.1
 * http://twitter.github.com/bootstrap/javascript.html#buttons
 * ============================================================
 * Copyright 2012 Twitter, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ============================================================ */


!function ($) {

  "use strict"; // jshint ;_;


 /* BUTTON PUBLIC CLASS DEFINITION
  * ============================== */

  var Button = function (element, options) {
    this.$element = $(element)
    this.options = $.extend({}, $.fn.button.defaults, options)
  }

  Button.prototype.setState = function (state) {
    var d = 'disabled'
      , $el = this.$element
      , data = $el.data()
      , val = $el.is('input') ? 'val' : 'html'

    state = state + 'Text'
    data.resetText || $el.data('resetText', $el[val]())

    $el[val](data[state] || this.options[state])

    // push to event loop to allow forms to submit
    setTimeout(function () {
      state == 'loadingText' ?
        $el.addClass(d).attr(d, d) :
        $el.removeClass(d).removeAttr(d)
    }, 0)
  }

  Button.prototype.toggle = function () {
    var $parent = this.$element.closest('[data-toggle="buttons-radio"]')

    $parent && $parent
      .find('.active')
      .removeClass('active')

    this.$element.toggleClass('active')
  }


 /* BUTTON PLUGIN DEFINITION
  * ======================== */

  $.fn.button = function (option) {
    return this.each(function () {
      var $this = $(this)
        , data = $this.data('button')
        , options = typeof option == 'object' && option
      if (!data) $this.data('button', (data = new Button(this, options)))
      if (option == 'toggle') data.toggle()
      else if (option) data.setState(option)
    })
  }

  $.fn.button.defaults = {
    loadingText: 'loading...'
  }

  $.fn.button.Constructor = Button


 /* BUTTON DATA-API
  * =============== */

  $(document).on('click.button.data-api', '[data-toggle^=button]', function (e) {
    var $btn = $(e.target)
    if (!$btn.hasClass('btn')) $btn = $btn.closest('.btn')
    $btn.button('toggle')
  })

}(window.jQuery);
/* =============================================================
 * bootstrap-collapse.js v2.2.1
 * http://twitter.github.com/bootstrap/javascript.html#collapse
 * =============================================================
 * Copyright 2012 Twitter, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ============================================================ */


!function ($) {

  "use strict"; // jshint ;_;


 /* COLLAPSE PUBLIC CLASS DEFINITION
  * ================================ */

  var Collapse = function (element, options) {
    this.$element = $(element)
    this.options = $.extend({}, $.fn.collapse.defaults, options)

    if (this.options.parent) {
      this.$parent = $(this.options.parent)
    }

    this.options.toggle && this.toggle()
  }

  Collapse.prototype = {

    constructor: Collapse

  , dimension: function () {
      var hasWidth = this.$element.hasClass('width')
      return hasWidth ? 'width' : 'height'
    }

  , show: function () {
      var dimension
        , scroll
        , actives
        , hasData

      if (this.transitioning) return

      dimension = this.dimension()
      scroll = $.camelCase(['scroll', dimension].join('-'))
      actives = this.$parent && this.$parent.find('> .accordion-group > .in')

      if (actives && actives.length) {
        hasData = actives.data('collapse')
        if (hasData && hasData.transitioning) return
        actives.collapse('hide')
        hasData || actives.data('collapse', null)
      }

      this.$element[dimension](0)
      this.transition('addClass', $.Event('show'), 'shown')
      $.support.transition && this.$element[dimension](this.$element[0][scroll])
    }

  , hide: function () {
      var dimension
      if (this.transitioning) return
      dimension = this.dimension()
      this.reset(this.$element[dimension]())
      this.transition('removeClass', $.Event('hide'), 'hidden')
      this.$element[dimension](0)
    }

  , reset: function (size) {
      var dimension = this.dimension()

      this.$element
        .removeClass('collapse')
        [dimension](size || 'auto')
        [0].offsetWidth

      this.$element[size !== null ? 'addClass' : 'removeClass']('collapse')

      return this
    }

  , transition: function (method, startEvent, completeEvent) {
      var that = this
        , complete = function () {
            if (startEvent.type == 'show') that.reset()
            that.transitioning = 0
            that.$element.trigger(completeEvent)
          }

      this.$element.trigger(startEvent)

      if (startEvent.isDefaultPrevented()) return

      this.transitioning = 1

      this.$element[method]('in')

      $.support.transition && this.$element.hasClass('collapse') ?
        this.$element.one($.support.transition.end, complete) :
        complete()
    }

  , toggle: function () {
      this[this.$element.hasClass('in') ? 'hide' : 'show']()
    }

  }


 /* COLLAPSIBLE PLUGIN DEFINITION
  * ============================== */

  $.fn.collapse = function (option) {
    return this.each(function () {
      var $this = $(this)
        , data = $this.data('collapse')
        , options = typeof option == 'object' && option
      if (!data) $this.data('collapse', (data = new Collapse(this, options)))
      if (typeof option == 'string') data[option]()
    })
  }

  $.fn.collapse.defaults = {
    toggle: true
  }

  $.fn.collapse.Constructor = Collapse


 /* COLLAPSIBLE DATA-API
  * ==================== */

  $(document).on('click.collapse.data-api', '[data-toggle=collapse]', function (e) {
    var $this = $(this), href
      , target = $this.attr('data-target')
        || e.preventDefault()
        || (href = $this.attr('href')) && href.replace(/.*(?=#[^\s]+$)/, '') //strip for ie7
      , option = $(target).data('collapse') ? 'toggle' : $this.data()
    $this[$(target).hasClass('in') ? 'addClass' : 'removeClass']('collapsed')
    $(target).collapse(option)
  })

}(window.jQuery);
/* ==========================================================
 * bootstrap-carousel.js v2.2.1
 * http://twitter.github.com/bootstrap/javascript.html#carousel
 * ==========================================================
 * Copyright 2012 Twitter, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ========================================================== */


!function ($) {

  "use strict"; // jshint ;_;


 /* CAROUSEL CLASS DEFINITION
  * ========================= */

  var Carousel = function (element, options) {
    this.$element = $(element)
	this.$indicators = this.$element.find('.carousel-indicators')
    this.options = options
    this.options.slide && this.slide(this.options.slide)
    this.options.pause == 'hover' && this.$element
      .on('mouseenter', $.proxy(this.pause, this))
      .on('mouseleave', $.proxy(this.cycle, this))
  }

  Carousel.prototype = {

    cycle: function (e) {
      if (!e) this.paused = false
      this.options.interval
        && !this.paused
        && (this.interval = setInterval($.proxy(this.next, this), this.options.interval))
      return this
    }
   , getActiveIndex: function () {
      this.$active = this.$element.find('.item-s.active')
      this.$items = this.$active.parent().children()
      return this.$items.index(this.$active)
    }	

  , to: function (pos) {
	  var activeIndex = this.getActiveIndex()
	  , that = this
      var $active = this.$element.find('.item-s.active')
        , children = $active.parent().children()
        , activePos = children.index($active)
        , that = this

      if (pos > (children.length - 1) || pos < 0) return

      if (this.sliding) {
        return this.$element.one('slid', function () {
          that.to(pos)
        })
      }
      
      if (activePos == pos) {
        return this.pause().cycle()
      }
     if (activeIndex == pos) {
        return this.pause().cycle()
      }

      return this.slide(pos > activeIndex ? 'next' : 'prev', $(this.$items[pos]))
      return this.slide(pos > activePos ? 'next' : 'prev', $(children[pos]))
    }

  , pause: function (e) {
      if (!e) this.paused = true
      if (this.$element.find('.next, .prev').length && $.support.transition.end) {
        this.$element.trigger($.support.transition.end)
        this.cycle()
      }
      clearInterval(this.interval)
      this.interval = null
      return this
    }

  , next: function () {
      if (this.sliding) return
      return this.slide('next')
    }

  , prev: function () {
      if (this.sliding) return
      return this.slide('prev')
    }

  , slide: function (type, next) {
      var $active = this.$element.find('.item-s.active')
        , $next = next || $active[type]()
        , isCycling = this.interval
        , direction = type == 'next' ? 'left' : 'right'
        , fallback  = type == 'next' ? 'first' : 'last'
        , that = this
        , e

      this.sliding = true

      isCycling && this.pause()

      $next = $next.length ? $next : this.$element.find('.item-s')[fallback]()

      e = $.Event('slide', {
        relatedTarget: $next[0]
      })

      if ($next.hasClass('active')) return
	  
	  if (this.$indicators.length) {
        this.$indicators.find('.active').removeClass('active')
        this.$element.one('slid', function () {
          var $nextIndicator = $(that.$indicators.children()[that.getActiveIndex()])
          $nextIndicator && $nextIndicator.addClass('active')
        })
      }

      if ($.support.transition && this.$element.hasClass('slide')) {
        this.$element.trigger(e)
        if (e.isDefaultPrevented()) return
        $next.addClass(type)
        $next[0].offsetWidth // force reflow
        $active.addClass(direction)
        $next.addClass(direction)
        this.$element.one($.support.transition.end, function () {
          $next.removeClass([type, direction].join(' ')).addClass('active')
          $active.removeClass(['active', direction].join(' '))
          that.sliding = false
          setTimeout(function () { that.$element.trigger('slid') }, 0)
        })
      } else {
        this.$element.trigger(e)
        if (e.isDefaultPrevented()) return
        $active.removeClass('active')
        $next.addClass('active')
        this.sliding = false
        this.$element.trigger('slid')
      }

      isCycling && this.cycle()

      return this
    }

  }


 /* CAROUSEL PLUGIN DEFINITION
  * ========================== */

  $.fn.carousel = function (option) {
    return this.each(function () {
      var $this = $(this)
        , data = $this.data('carousel')
        , options = $.extend({}, $.fn.carousel.defaults, typeof option == 'object' && option)
        , action = typeof option == 'string' ? option : options.slide
      if (!data) $this.data('carousel', (data = new Carousel(this, options)))
      if (typeof option == 'number') data.to(option)
      else if (action) data[action]()
      else if (options.interval) data.cycle()
    })
  }

  $.fn.carousel.defaults = {
    interval: 5000
  , pause: 'hover'
  }

  $.fn.carousel.Constructor = Carousel


 /* CAROUSEL DATA-API
  * ================= */

  $(document).on('click.carousel.data-api', '[data-slide]', function (e) {
    var $this = $(this), href
      , $target = $($this.attr('data-target') || (href = $this.attr('href')) && href.replace(/.*(?=#[^\s]+$)/, '')) //strip for ie7
      , options = $.extend({}, $target.data(), $this.data())
    $target.carousel(options)
    e.preventDefault()
  })

}(window.jQuery);
/* =============================================================
 * bootstrap-typeahead.js v2.2.1
 * http://twitter.github.com/bootstrap/javascript.html#typeahead
 * =============================================================
 * Copyright 2012 Twitter, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ============================================================ */


!function($){

  "use strict"; // jshint ;_;


 /* TYPEAHEAD PUBLIC CLASS DEFINITION
  * ================================= */

  var Typeahead = function (element, options) {
    this.$element = $(element)
    this.options = $.extend({}, $.fn.typeahead.defaults, options)
    this.matcher = this.options.matcher || this.matcher
    this.sorter = this.options.sorter || this.sorter
    this.highlighter = this.options.highlighter || this.highlighter
    this.updater = this.options.updater || this.updater
    this.$menu = $(this.options.menu).appendTo('body')
    this.source = this.options.source
    this.shown = false
    this.listen()
  }

  Typeahead.prototype = {

    constructor: Typeahead

  , select: function () {
      var val = this.$menu.find('.active').attr('data-value')
      this.$element
        .val(this.updater(val))
        .change()
      return this.hide()
    }

  , updater: function (item) {
      return item
    }

  , show: function () {
      var pos = $.extend({}, this.$element.offset(), {
        height: this.$element[0].offsetHeight
      })

      this.$menu.css({
        top: pos.top + pos.height
      , left: pos.left
      })

      this.$menu.show()
      this.shown = true
      return this
    }

  , hide: function () {
      this.$menu.hide()
      this.shown = false
      return this
    }

  , lookup: function (event) {
      var items

      this.query = this.$element.val()

      if (!this.query || this.query.length < this.options.minLength) {
        return this.shown ? this.hide() : this
      }

      items = $.isFunction(this.source) ? this.source(this.query, $.proxy(this.process, this)) : this.source

      return items ? this.process(items) : this
    }

  , process: function (items) {
      var that = this

      items = $.grep(items, function (item) {
        return that.matcher(item)
      })

      items = this.sorter(items)

      if (!items.length) {
        return this.shown ? this.hide() : this
      }

      return this.render(items.slice(0, this.options.items)).show()
    }

  , matcher: function (item) {
      return ~item.toLowerCase().indexOf(this.query.toLowerCase())
    }

  , sorter: function (items) {
      var beginswith = []
        , caseSensitive = []
        , caseInsensitive = []
        , item

      while (item = items.shift()) {
        if (!item.toLowerCase().indexOf(this.query.toLowerCase())) beginswith.push(item)
        else if (~item.indexOf(this.query)) caseSensitive.push(item)
        else caseInsensitive.push(item)
      }

      return beginswith.concat(caseSensitive, caseInsensitive)
    }

  , highlighter: function (item) {
      var query = this.query.replace(/[\-\[\]{}()*+?.,\\\^$|#\s]/g, '\\$&')
      return item.replace(new RegExp('(' + query + ')', 'ig'), function ($1, match) {
        return '<strong>' + match + '</strong>'
      })
    }

  , render: function (items) {
      var that = this

      items = $(items).map(function (i, item) {
        i = $(that.options.item).attr('data-value', item)
        i.find('a').html(that.highlighter(item))
        return i[0]
      })

      items.first().addClass('active')
      this.$menu.html(items)
      return this
    }

  , next: function (event) {
      var active = this.$menu.find('.active').removeClass('active')
        , next = active.next()

      if (!next.length) {
        next = $(this.$menu.find('li')[0])
      }

      next.addClass('active')
    }

  , prev: function (event) {
      var active = this.$menu.find('.active').removeClass('active')
        , prev = active.prev()

      if (!prev.length) {
        prev = this.$menu.find('li').last()
      }

      prev.addClass('active')
    }

  , listen: function () {
      this.$element
        .on('blur',     $.proxy(this.blur, this))
        .on('keypress', $.proxy(this.keypress, this))
        .on('keyup',    $.proxy(this.keyup, this))

      if (this.eventSupported('keydown')) {
        this.$element.on('keydown', $.proxy(this.keydown, this))
      }

      this.$menu
        .on('click', $.proxy(this.click, this))
        .on('mouseenter', 'li', $.proxy(this.mouseenter, this))
    }

  , eventSupported: function(eventName) {
      var isSupported = eventName in this.$element
      if (!isSupported) {
        this.$element.setAttribute(eventName, 'return;')
        isSupported = typeof this.$element[eventName] === 'function'
      }
      return isSupported
    }

  , move: function (e) {
      if (!this.shown) return

      switch(e.keyCode) {
        case 9: // tab
        case 13: // enter
        case 27: // escape
          e.preventDefault()
          break

        case 38: // up arrow
          e.preventDefault()
          this.prev()
          break

        case 40: // down arrow
          e.preventDefault()
          this.next()
          break
      }

      e.stopPropagation()
    }

  , keydown: function (e) {
      this.suppressKeyPressRepeat = !~$.inArray(e.keyCode, [40,38,9,13,27])
      this.move(e)
    }

  , keypress: function (e) {
      if (this.suppressKeyPressRepeat) return
      this.move(e)
    }

  , keyup: function (e) {
      switch(e.keyCode) {
        case 40: // down arrow
        case 38: // up arrow
        case 16: // shift
        case 17: // ctrl
        case 18: // alt
          break

        case 9: // tab
        case 13: // enter
          if (!this.shown) return
          this.select()
          break

        case 27: // escape
          if (!this.shown) return
          this.hide()
          break

        default:
          this.lookup()
      }

      e.stopPropagation()
      e.preventDefault()
  }

  , blur: function (e) {
      var that = this
      setTimeout(function () { that.hide() }, 150)
    }

  , click: function (e) {
      e.stopPropagation()
      e.preventDefault()
      this.select()
    }

  , mouseenter: function (e) {
      this.$menu.find('.active').removeClass('active')
      $(e.currentTarget).addClass('active')
    }

  }


  /* TYPEAHEAD PLUGIN DEFINITION
   * =========================== */

  $.fn.typeahead = function (option) {
    return this.each(function () {
      var $this = $(this)
        , data = $this.data('typeahead')
        , options = typeof option == 'object' && option
      if (!data) $this.data('typeahead', (data = new Typeahead(this, options)))
      if (typeof option == 'string') data[option]()
    })
  }

  $.fn.typeahead.defaults = {
    source: []
  , items: 8
  , menu: '<ul class="typeahead dropdown-menu"></ul>'
  , item: '<li><a href="#"></a></li>'
  , minLength: 1
  }

  $.fn.typeahead.Constructor = Typeahead


 /*   TYPEAHEAD DATA-API
  * ================== */

  $(document).on('focus.typeahead.data-api', '[data-provide="typeahead"]', function (e) {
    var $this = $(this)
    if ($this.data('typeahead')) return
    e.preventDefault()
    $this.typeahead($this.data())
  })

}(window.jQuery);

/* ===========================================================
 * bootstrap-fileupload.js j2
 * http://jasny.github.com/bootstrap/javascript.html#fileupload
 * ===========================================================
 * Copyright 2012 Jasny BV, Netherlands.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ========================================================== */

!function ($) {

  "use strict"; // jshint ;_

 /* INPUTMASK PUBLIC CLASS DEFINITION
  * ================================= */

  var Fileupload = function (element, options) {
    this.$element = $(element)
    this.type = this.$element.data('uploadtype') || (this.$element.find('.thumbnail').length > 0 ? "image" : "file")
      
    this.$input = this.$element.find(':file')
    if (this.$input.length === 0) return

    this.name = this.$input.attr('name') || options.name

    this.$hidden = this.$element.find(':hidden[name="'+this.name+'"]')
    if (this.$hidden.length === 0) {
      this.$hidden = $('<input type="hidden" />')
      this.$element.prepend(this.$hidden)
    }

    this.$preview = this.$element.find('.fileupload-preview')
    var height = this.$preview.css('height')
    if (this.$preview.css('display') != 'inline' && height != '0px' && height != 'none') this.$preview.css('line-height', height)

    this.$remove = this.$element.find('[data-dismiss="fileupload"]')

    this.$element.find('[data-trigger="fileupload"]').on('click.fileupload', $.proxy(this.trigger, this))

    this.listen()
  }
  
  Fileupload.prototype = {
    
    listen: function() {
      this.$input.on('change.fileupload', $.proxy(this.change, this))
      if (this.$remove) this.$remove.on('click.fileupload', $.proxy(this.clear, this))
    },
    
    change: function(e, invoked) {
      var file = e.target.files !== undefined ? e.target.files[0] : (e.target.value ? { name: e.target.value.replace(/^.+\\/, '') } : null)
      if (invoked === 'clear') return
      
      if (!file) {
        this.clear()
        return
      }
      
      this.$hidden.val('')
      this.$hidden.attr('name', '')
      this.$input.attr('name', this.name)

      if (this.type === "image" && this.$preview.length > 0 && (typeof file.type !== "undefined" ? file.type.match('image.*') : file.name.match('\\.(gif|png|jpe?g)$')) && typeof FileReader !== "undefined") {
        var reader = new FileReader()
        var preview = this.$preview
        var element = this.$element

        reader.onload = function(e) {
          preview.html('<img src="' + e.target.result + '" ' + (preview.css('max-height') != 'none' ? 'style="max-height: ' + preview.css('max-height') + ';"' : '') + ' />')
          element.addClass('fileupload-exists').removeClass('fileupload-new')
        }

        reader.readAsDataURL(file)
      } else {
        this.$preview.text(file.name)
        this.$element.addClass('fileupload-exists').removeClass('fileupload-new')
      }
    },

    clear: function(e) {
      this.$hidden.val('')
      this.$hidden.attr('name', this.name)
      this.$input.attr('name', '')
      this.$input.val('') // Doesn't work in IE, which causes issues when selecting the same file twice

      this.$preview.html('')
      this.$element.addClass('fileupload-new').removeClass('fileupload-exists')

      if (e) {
        this.$input.trigger('change', [ 'clear' ])
        e.preventDefault()
      }
    },
    
    trigger: function(e) {
      this.$input.trigger('click')
      e.preventDefault()
    }
  }

  
 /* INPUTMASK PLUGIN DEFINITION
  * =========================== */

  $.fn.fileupload = function (options) {
    return this.each(function () {
      var $this = $(this)
      , data = $this.data('fileupload')
      if (!data) $this.data('fileupload', (data = new Fileupload(this, options)))
    })
  }

  $.fn.fileupload.Constructor = Fileupload


 /* INPUTMASK DATA-API
  * ================== */

  $(function () {
    $('body').on('click.fileupload.data-api', '[data-provides="fileupload"]', function (e) {
      var $this = $(this)
      if ($this.data('fileupload')) return
      $this.fileupload($this.data())
      
      var $target = $(e.target).parents('[data-dismiss=fileupload],[data-trigger=fileupload]').first()
      if ($target.length > 0) {
          $target.trigger('click.fileupload')
          e.preventDefault()
      }
    })
  })

}(window.jQuery)

/* ===========================================================
 * bootstrap-select.js 
 * 
 * ========================================================== */
!function($) {
    var Selectpicker = function(element, options, e) {
        if (e ) {
            e.stopPropagation();
            e.preventDefault();
        }
        this.$element = $(element);
        this.$newElement = null;
        this.button = null;
        this.options = $.extend({}, $.fn.selectpicker.defaults, this.$element.data(), typeof options == 'object' && options);
        this.style = this.options.style;
        this.size = this.options.size;
        this.init();
    };

    Selectpicker.prototype = {

        constructor: Selectpicker,

        init: function (e) {
            this.$element.hide();
            //var classList = this.$element.attr('class') !== undefined ? this.$element.attr('class').split(/\s+/) : '';
            var template = this.getTemplate();
            var id = this.$element.attr('id');
            template = this.createLi(template);
            this.$element.after(template);
            this.$newElement = this.$element.next('.bootstrap-select');
            var select = this.$newElement;
            var menu = this.$newElement.find('.dropdown-menu');
            var menuA = this.$newElement.find('.dropdown-menu ul li > a');
            var liHeight = parseInt(menuA.css('line-height')) + menuA.outerHeight();
            var selectOffset_top = this.$newElement.offset().top;
            var size = 0;
            var menuHeight = 0;
            var selectHeight = this.$newElement.outerHeight();
            this.button = this.$newElement.find('> button');
            if (id !== undefined) {
                this.button.attr('id', id);
                $('label[for="' + id + '"]').click(function(){ select.find('button#'+id).focus(); })
            }
            /*for (var i = 0; i < classList.length; i++) {
                if(classList[i] != 'selectpicker') {
                    this.$newElement.addClass(classList[i]);
                }
            }*/
            this.button.addClass(this.style);
            this.checkDisabled();
			this.checkTabIndex();
            this.clickListener();
            this.$element.find('optgroup').each(function() {
                if ($(this).attr('label')) {
                    menu.find('.opt'+$(this).index()).eq(0).before('<dt>'+$(this).attr('label')+'</dt>');
                }
                menu.find('.opt'+$(this).index()).eq(0).parent().prev().addClass('optgroup-div');
            });
            if (this.size == 'auto') {
                function getSize() {
                    var selectOffset_top_scroll = selectOffset_top - $(window).scrollTop();
                    var windowHeight = window.innerHeight;
                    var menuExtras = parseInt(menu.css('padding-top')) + parseInt(menu.css('padding-bottom')) + parseInt(menu.css('border-top-width')) + parseInt(menu.css('border-bottom-width')) + parseInt(menu.css('margin-top')) + parseInt(menu.css('margin-bottom')) + 2;
                    var selectOffset_bot = windowHeight - selectOffset_top_scroll - selectHeight - menuExtras;
                    if (!select.hasClass('dropup')) {
                    size = Math.floor(selectOffset_bot/liHeight);
                    } else {
                    size = Math.floor((selectOffset_top_scroll - menuExtras)/liHeight);
                    }
                    if (size < 4) {size = 3};
                    menuHeight = liHeight*size;
                    if (menu.find('ul li').length + menu.find('dt').length > size) {
                        menu.find('ul').css({'max-height' : '156px', 'overflow-y' : 'scroll'});
                    } else {
                        menu.find('ul').css({'max-height' : '156px', 'overflow-y' : 'auto'});
                    }
					/* var winheight = $(window).height();
					if(selectOffset_top_scroll > winheight/2){
						menu.css({"bottom":"100%","top":"inherit"});
					}else{
						menu.css({"top":"100%","bottom":"inherit"})
					}*/
            }
                getSize();
                $(window).resize(getSize);
                $(window).scroll(function(){
                	var selectOffset_top_scroll = selectOffset_top - $(window).scrollTop();
                	var winheight = $(window).height();
                	menu.find('ul').css({'max-height' : '156px', 'overflow-y' : 'scroll'});
					if(selectOffset_top_scroll > winheight/2){
						menu.css({"bottom":"100%","top":"inherit"});
					}else{
						menu.css({"top":"100%","bottom":"inherit"});
					}
                });
            } else if (this.size && this.size != 'auto' && menu.find('ul li').length > this.size) {
                menuHeight = liHeight*this.size;
                if (this.size == 1) {menuHeight = menuHeight + 8}
                menu.find('ul').css({'max-height' : menuHeight + 'px', 'overflow-y' : 'scroll'});
            }
            this.$element.bind('DOMNodeInserted', $.proxy(this.reloadLi, this));
        },

        getTemplate: function() {
            var template =
                "<div class='btn-group bootstrap-select'>" +
                    "<button class='btn dropdown-toggle clearfix' data-toggle='dropdown'>" +
                        "<span class='filter-option pull-left'>__SELECTED_OPTION</span>&nbsp;" +
                        "<span class='caret'></span>" +
                    "</button>" +
                    "<div class='dropdown-menu' role='menu'>" +
                        "<ul>" +
                            "__ADD_LI" +
                        "</ul>" +
                    "</div>" +
                "</div>";

            return template;
        },

        reloadLi: function() {
            var _li = [];
            var _liHtml = '';

            this.$newElement.find('li').remove();

            this.$element.find('option').each(function(){
                _li.push($(this).text());
            });

            if(_li.length > 0) {
                for (var i = 0; i < _li.length; i++) {
                    this.$newElement.find('ul').append(
                        '<li rel=' + i + '><a tabindex="-1" href="#">' + _li[i] + '</a></li>'
                    );
                }
            }
        },

        createLi: function(template) {

            var _li = [];
            var _liA = [];
            var _liHtml = '';
            var opt_index = null;
            var _this = this;
            var _selected_index = this.$element[0].selectedIndex ? this.$element[0].selectedIndex : 0;

            this.$element.find('option').each(function(){
                _li.push($(this).text());
            });

            this.$element.find('option').each(function() {
                if ($(this).parent().is('optgroup')) {
                    opt_index = String($(this).parent().index());
                    var optgroup = $(this).parent();
                    for (var i = 0; i < optgroup.length; i++) {
                        _liA.push('<a class="opt'+opt_index[i]+'" tabindex="-1" href="#">'+$(this).text()+'</a>');
                    }

                } else {
                    _liA.push('<a tabindex="-1" href="#">'+$(this).text()+'</a>');
                }
            });

            if (_li.length > 0) {
                template = template.replace('__SELECTED_OPTION', _li[_selected_index]);
                for (var i = 0; i < _li.length; i++) {
                    _liHtml += "<li rel=" + i + ">" + _liA[i] + "</li>";
                }
            }

            this.$element.find('option').eq(_selected_index).prop('selected',true);

            template = template.replace('__ADD_LI', _liHtml);

            return template;
        },

        checkDisabled: function() {
            if (this.$element.is(':disabled')) {
                this.button.addClass('disabled');
                this.button.click(function(e) {
                    e.preventDefault();
                });
            }
        },
		
		checkTabIndex: function() {
			if (this.$element.is('[tabindex]')) {
				var tabindex = this.$element.attr("tabindex");
				this.button.attr('tabindex', tabindex);
			}
		},

        clickListener: function() {
            $('body').on('touchstart.dropdown', '.dropdown-menu', function (e) { e.stopPropagation(); });
            $('.dropdown-menu').find('li dt').on('click', function(e) {
                e.stopPropagation();
            });
            $(this.$newElement).on('click', 'li a', function(e){
                e.preventDefault();
                var selected = $(this).parent().index(),
                    $this = $(this).parent(),
                    $select = $this.parents('.bootstrap-select');

                if ($select.prev('select').not(':disabled')){

                    $select.prev('select').find('option').removeAttr('selected');
                    $select.prev('select').find('option').eq(selected).prop('selected', true).attr('selected', 'selected');
                    $select.find('.filter-option').html($this.text());
                    $select.find('button').focus();

                    // Trigger select 'change'
                    $select.prev('select').trigger('change');
                }

            });
            this.$element.on('change', function(e) {
                if($(this).find('option:selected').attr('title')!=undefined){
                    $(this).next('.bootstrap-select').find('.filter-option').html($(this).find('option:selected').attr('title'));
                }else{
                    $(this).next('.bootstrap-select').find('.filter-option').html($(this).find('option:selected').text());
                }
            });
        }

    };

    $.fn.selectpicker = function(option, event) {
        return this.each(function () {
            var $this = $(this),
                data = $this.data('selectpicker'),
                options = typeof option == 'object' && option;
            if (!data) {
                $this.data('selectpicker', (data = new Selectpicker(this, options, event)));
            }
            if (typeof option == 'string') {
                data[option]();
            }
        });
    };

    $.fn.selectpicker.defaults = {
        style: null,
        size: 'auto'
    }

}(window.jQuery);

/* ============================================================
 * bootstrap-tokenfield.js v0.9.9-2
 * ============================================================
 *
 * Copyright 2013 Sliptree
 * ============================================================ */

!function ($) {

  "use strict"; // jshint ;_;

 /* TOKENFIELD PUBLIC CLASS DEFINITION
  * ============================== */

  var Tokenfield = function (element, options) {
    var _self = this

    this.$element = $(element)
    // Extend options
    this.options = $.extend({}, $.fn.tokenfield.defaults, { tokens: this.$element.val() }, options)    
    
    // Store original input width
    var elRules = (typeof window.getMatchedCSSRules === 'function') ? window.getMatchedCSSRules( element ) : null
      , elStyleWidth = element.style.width
      , elCSSWidth
      , elWidth = this.$element.width()

    if (elRules) {
      $.each( elRules, function (i, rule) {
        if (rule.style.width) {
          elCSSWidth = rule.style.width;
        }
      });
    }

    // Move original input out of the way
    this.$element.css({
      'position': 'absolute',
      'left': '-10000px'
    }).prop('tabindex', -1);

    // Create a wrapper
    this.$wrapper = $('<div class="tokenfield form-control" />')
    if (this.$element.hasClass('input-lg')) this.$wrapper.addClass('input-lg')
    if (this.$element.hasClass('input-sm')) this.$wrapper.addClass('input-sm')

    // Create a new input
    this.$input = $('<input type="text" class="token-input" />')
                    .appendTo( this.$wrapper )
                    .prop( 'placeholder',  this.$element.prop('placeholder') )
                    .prop( 'id', this.$element.prop('id') + '-tokenfield' )

    // Re-route original input label to new input
    var $label = $( 'label[for="' + this.$element.prop('id') + '"]' )
    if ( $label.length ) {
      $label.prop( 'for', this.$input.prop('id') )
    }

    // Set up a copy helper to handle copy & paste
    this.$copyHelper = $('<input type="text" />').css({
      'position': 'absolute',
      'left': '-10000px'
    }).prop('tabindex', -1).prependTo( this.$wrapper )
    
    // Set wrapper width
    if (elStyleWidth) {
      this.$wrapper.css('width', elStyleWidth);
    }
    else if (elCSSWidth) {
      this.$wrapper.css('width', elCSSWidth);
    }
    // If input is inside inline-form with no width set, set fixed width
    else if (this.$element.parents('.form-inline').length) {
      this.$wrapper.width( elWidth )
    }

    // Set tokenfield disabled, if original or fieldset input is disabled
    if (this.$element.prop('disabled') || this.$element.parents('fieldset[disabled]').length) {
      this.disable();
    }

    // Set up mirror for input auto-sizing
    this.$mirror = $('<span style="position:absolute; top:-999px; left:0; white-space:pre;"/>');
    this.$input.css('min-width', this.options.minWidth + 'px')
    $.each([
        'fontFamily', 
        'fontSize', 
        'fontWeight', 
        'fontStyle', 
        'letterSpacing', 
        'textTransform', 
        'wordSpacing', 
        'textIndent'
    ], function (i, val) {
        _self.$mirror[0].style[val] = _self.$input.css(val);
    });
    this.$mirror.appendTo( 'body' )

    // Insert tokenfield to HTML
    this.$wrapper.insertBefore( this.$element )
    this.$element.prependTo( this.$wrapper )

    // Calculate inner input width
    this.update()
    
    // Create initial tokens, if any
    this.setTokens(this.options.tokens, false, false)

    // Start listening to events
    this.listen()

    // Initialize autocomplete, if necessary
    if ( ! $.isEmptyObject( this.options.autocomplete ) ) {
      var autocompleteOptions = $.extend({}, this.options.autocomplete, {
        minLength: this.options.showAutocompleteOnFocus ? 0 : null,
        position: { my: "left top", at: "left bottom", of: this.$wrapper }
      })
      this.$input.autocomplete( autocompleteOptions )
    }

    // Initialize typeahead, if necessary
    if ( ! $.isEmptyObject( this.options.typeahead ) ) {
      var typeaheadOptions = $.extend({}, this.options.typeahead, {})
      this.$input.typeahead( typeaheadOptions )
      this.typeahead = true
    }
  }

  Tokenfield.prototype = {

    constructor: Tokenfield

  , createToken: function (attrs, triggerChange) {
      if (typeof attrs === 'string') {
        attrs = { value: attrs, label: attrs }
      }

     if (typeof triggerChange === 'undefined') {
         triggerChange = true
     }
      
      var _self = this
        , value = $.trim(attrs.value)
        , label = attrs.label.length ? $.trim(attrs.label) : value

      if (!value.length || !label.length || value.length < this.options.minLength) return

      if (!this.options.allowDuplicates && $.grep(this.getTokens(), function (token) {
        return token.value === value
      }).length) {
        // Allow listening to when duplicates get prevented
        var duplicateEvent = $.Event('preventDuplicateToken')
        duplicateEvent.token = {
          value: value,
          label: label
        }
        this.$element.trigger( duplicateEvent )        
        return
      }

      // Allow changing token data before creating it
      var beforeCreateEvent = $.Event('beforeCreateToken')
      beforeCreateEvent.token = {
        value: value,
        label: label
      }
      this.$element.trigger( beforeCreateEvent )

      if (!beforeCreateEvent.token) return

      value = beforeCreateEvent.token.value
      label = beforeCreateEvent.token.label

      var token = $('<div class="token" />')
            .attr('data-value', value)
            .append('<span class="token-label" />')
            .append('<a href="#" class="close" tabindex="-1">&times;</a>')

      // Insert token into HTML
      if (this.$input.hasClass('tt-query')) {
        this.$input.parent().before( token )
      } else {
        this.$input.before( token )
      }
      this.$input.css('width', this.options.minWidth + 'px')

      var tokenLabel = token.find('.token-label')
        , closeButton = token.find('.close')

      // Determine maximum possible token label width
      if (!this.maxTokenWidth) {
        this.maxTokenWidth =
          this.$wrapper.width() - closeButton.outerWidth() - 
          parseInt(closeButton.css('margin-left'), 10) -
          parseInt(closeButton.css('margin-right'), 10) -
          parseInt(token.css('border-left-width'), 10) -
          parseInt(token.css('border-right-width'), 10) -
          parseInt(token.css('padding-left'), 10) -
          parseInt(token.css('padding-right'), 10)
          parseInt(tokenLabel.css('border-left-width'), 10) -
          parseInt(tokenLabel.css('border-right-width'), 10) -
          parseInt(tokenLabel.css('padding-left'), 10) -
          parseInt(tokenLabel.css('padding-right'), 10)
          parseInt(tokenLabel.css('margin-left'), 10) -
          parseInt(tokenLabel.css('margin-right'), 10)
      }

      tokenLabel
        .text(label)
        .css('max-width', this.maxTokenWidth)

      // Listen to events
      token
        .on('mousedown',  function (e) {
          if (_self.disabled) return false;
          _self.preventDeactivation = true
        })
        .on('click',    function (e) {
          if (_self.disabled) return false;
          _self.preventDeactivation = false

          if (e.ctrlKey || e.metaKey) {
            e.preventDefault()
            return _self.toggle( token )
          }
          
          _self.activate( token, e.shiftKey, e.shiftKey )          
        })
        .on('dblclick', function (e) {
          if (_self.disabled) return false;
          _self.edit( token )
        })

      closeButton
          .on('click',  $.proxy(this.remove, this))

      var afterCreateEvent = $.Event('afterCreateToken')
      afterCreateEvent.token = beforeCreateEvent.token
      afterCreateEvent.relatedTarget = token.get(0)
      this.$element.trigger( afterCreateEvent )

      var changeEvent = $.Event('change')
      changeEvent.initiator = 'tokenfield'
      if (triggerChange) {
        this.$element.val( this.getTokensList() ).trigger( changeEvent )
      }
      this.update()

      return this.$input.get(0)
    }    

  , setTokens: function (tokens, add, triggerChange) {
      if (!tokens) return

      if (!add) this.$wrapper.find('.token').remove()

      if (typeof triggerChange === 'undefined') {
          triggerChange = true
      }

      if (typeof tokens === 'string') {
        if (this.options.triggerKeys.length) {
          var delimiters = $.map(this.options.triggerKeys, function (charCode) {
            return String.fromCharCode(charCode)
          })
          tokens = tokens.split( new RegExp( '[' + delimiters.join('') + ']' ) )
        } else {
          tokens = [tokens];
        }
      }

      var _self = this
      $.each(tokens, function (i, token) {
        _self.createToken(token, triggerChange)
      })

      return this.$element.get(0)
    }

  , getTokenData: function(token) {
      var data = token.map(function() {
        var $token = $(this);
        return {
          value: $token.attr('data-value') || $token.find('.token-label').text(),
          label: $token.find('.token-label').text()
        }
      }).get();

      if (data.length == 1) {
        data = data[0];
      }

      return data;
    }

  , getTokens: function(active) {
      var self = this
        , tokens = []
        , activeClass = active ? '.active' : '' // get active tokens only
      this.$wrapper.find( '.token' + activeClass ).each( function() {
        tokens.push( self.getTokenData( $(this) ) )
      })
      return tokens
  }

  , getTokensList: function(active) {
      return $.map( this.getTokens(active), function (token) {
        return token.value
      }).join(', ')
  }

  , getInput: function() {
    return this.$input.val()
  }

  , listen: function () {
      var _self = this

      this.$element
        .on('change',   $.proxy(this.change, this))

      this.$wrapper
        .on('mousedown',$.proxy(this.focusInput, this))

      this.$input
        .on('focus',    $.proxy(this.focus, this))
        .on('blur',     $.proxy(this.blur, this))
        .on('paste',    $.proxy(this.paste, this))
        .on('keydown',  $.proxy(this.keydown, this))
        .on('keypress', $.proxy(this.keypress, this))
        .on('keyup',    $.proxy(this.keyup, this))

      this.$copyHelper
        .on('focus',    $.proxy(this.focus, this))
        .on('blur',     $.proxy(this.blur, this))        
        .on('keydown',  $.proxy(this.keydown, this))
        .on('keyup',    $.proxy(this.keyup, this))

      // Secondary listeners for input width calculation
      this.$input
        .on('keypress', $.proxy(this.update, this))
        .on('keyup',    $.proxy(this.update, this))

      this.$input
        .on('autocompletecreate', function() {
          // Set minimum autocomplete menu width
          var $_menuElement = $(this).data('ui-autocomplete').menu.element
          
          var minWidth = _self.$wrapper.outerWidth() -
              parseInt( $_menuElement.css('border-left-width'), 10 ) -
              parseInt( $_menuElement.css('border-right-width'), 10 )

          $_menuElement.css( 'min-width', minWidth + 'px' )
        })
        .on('autocompleteselect', function (e, ui) {
          _self.$input.val('')
          _self.createToken( ui.item )
          if (_self.$input.data( 'edit' )) {
            _self.unedit(true)
          }
          return false
        })
        .on('typeahead:selected', function (e, datum, dataset) {
          var valueKey = 'value'

          // Get the actual valueKey for this dataset
          $.each(_self.$input.data('ttView').datasets, function (i, set) {
            if (set.name === dataset) {
              valueKey = set.valueKey
            }
          })

          // Create token
          _self.createToken( datum[valueKey] )
          _self.$input.typeahead('setQuery', '')
          if (_self.$input.data( 'edit' )) {
            _self.unedit(true)
          }
        })
        .on('typeahead:autocompleted', function (e, datum, dataset) {
          _self.createToken( _self.$input.val() )
          _self.$input.typeahead('setQuery', '')
          if (_self.$input.data( 'edit' )) {
            _self.unedit(true)
          }
        })

      // Listen to window resize
      $(window).on('resize', $.proxy(this.update, this ))

    }

  , keydown: function (e) {

      if (!this.focused) return

      switch(e.keyCode) {
        case 8: // backspace
          if (!this.$input.is(':focus')) break
          this.lastInputValue = this.$input.val()
          break

        case 37: // left arrow
          if (this.$input.is(':focus')) {
            if (this.$input.val().length > 0) break

            var prev = this.$input.hasClass('tt-query') ? this.$input.parent().prevAll('.token:first') : this.$input.prevAll('.token:first')

            if (!prev.length) break

            this.preventInputFocus = true
            this.preventDeactivation = true

            this.activate( prev )
            e.preventDefault()

          } else {

            this.prev( e.shiftKey )
            e.preventDefault()
          }
          break

        case 38: // up arrow
          if (!e.shiftKey) return

          if (this.$input.is(':focus')) {
            if (this.$input.val().length > 0) break

            var prev = this.$input.hasClass('tt-query') ? this.$input.parent().prevAll('.token:last') : this.$input.prevAll('.token:last')
            if (!prev.length) return

            this.activate( prev )
          }

          var _self = this
          this.firstActiveToken.nextAll('.token').each(function() {
            _self.deactivate( $(this) )
          })

          this.activate( this.$wrapper.find('.token:first'), true, true )
          e.preventDefault()
          break

        case 39: // right arrow
          if (this.$input.is(':focus')) {

            if (this.$input.val().length > 0) break
            
            var next = this.$input.hasClass('tt-query') ? this.$input.parent().nextAll('.token:first') : this.$input.nextAll('.token:first')

            if (!next.length) break

            this.preventInputFocus = true
            this.preventDeactivation = true

            this.activate( next )
            e.preventDefault()              

          } else {
            this.next( e.shiftKey )
            e.preventDefault()
          }
          break

        case 40: // down arrow
          if (!e.shiftKey) return

          if (this.$input.is(':focus')) {
            if (this.$input.val().length > 0) break

            var next = this.$input.hasClass('tt-query') ? this.$input.parent().nextAll('.token:first') : this.$input.nextAll('.token:first')
            if (!next.length) return

            this.activate( next )
          }

          var _self = this
          this.firstActiveToken.prevAll('.token').each(function() {
            _self.deactivate( $(this) )
          })          

          this.activate( this.$wrapper.find('.token:last'), true, true )
          e.preventDefault()
          break        

        case 65: // a (to handle ctrl + a)
          if (this.$input.val().length > 0 || !(e.ctrlKey || e.metaKey)) break
          this.activateAll()
          e.preventDefault()
          break

        case 9: // tab
        case 13: // enter

          // We will handle creating tokens from autocomplete in autocomplete events
          if (this.$input.data('ui-autocomplete') && this.$input.data('ui-autocomplete').menu.element.find("li:has(a.ui-state-focus)").length) break
          // We will handle creating tokens from typeahead in typeahead events
          if (this.$input.hasClass('tt-query') && this.$wrapper.find('.tt-is-under-cursor').length ) break
          if (this.$input.hasClass('tt-query') && this.$wrapper.find('.tt-hint').val().length) break
          
          // Create token
          if (this.$input.is(':focus') && this.$input.val().length || this.$input.data('edit')) {
            this.createTokensFromInput(e, this.$input.data('edit'))
          }
          if (e.keyCode === 13) {
            if (!this.$copyHelper.is(':focus') || this.$wrapper.find('.token.active').length !== 1) break
            this.edit( this.$wrapper.find('.token.active') )
          }
      }

      this.lastKeyDown = e.keyCode
    }

  , keypress: function(e) {
      this.lastKeyPressCode = e.keyCode
      this.lastKeyPressCharCode = e.charCode

      // Comma
      if (~$.inArray( e.charCode, this.options.triggerKeys) && this.$input.is(':focus')) {
        if (this.$input.val()) {
          this.createTokensFromInput(e)
        }
        return false;
      }
    }

  , keyup: function (e) {
      this.preventInputFocus = false

      if (!this.focused) return

      switch(e.keyCode) {
        case 8: // backspace
          if (this.$input.is(':focus')) {
            if (this.$input.val().length || this.lastInputValue.length && this.lastKeyDown === 8) break
            
            this.preventDeactivation = true
            var prev = this.$input.hasClass('tt-query') ? this.$input.parent().prevAll('.token:first') : this.$input.prevAll('.token:first')

            if (!prev.length) break

            this.activate( prev )
          } else {
            this.remove(e)
          }
          break

        case 46: // delete
          this.remove(e, 'next')
          break
      }
      this.lastKeyUp = e.keyCode
    }

  , focus: function (e) {
      this.focused = true
      this.$wrapper.addClass('focus')

      if (this.$input.is(':focus')) {
        this.$wrapper.find('.active').removeClass('active')
        this.firstActiveToken = null

        if (this.options.showAutocompleteOnFocus) {
          this.search()
        }
      }
    }

  , blur: function (e) {

      this.focused = false
      this.$wrapper.removeClass('focus')

      if (!this.preventDeactivation && !this.$element.is(':focus')) {
        this.$wrapper.find('.active').removeClass('active')
        this.firstActiveToken = null
      }

      if (!this.preventCreateTokens && (this.$input.data('edit') && !this.$input.is(':focus') || this.options.createTokensOnBlur )) {
        this.createTokensFromInput(e) 
      }
      
      this.preventDeactivation = false
      this.preventCreateTokens = false
    }

  , paste: function (e) {
      var _self = this
      
      // Add tokens to existing ones
      setTimeout(function () {
        _self.createTokensFromInput(e)
      }, 1)
    }

  , change: function (e) {
      if ( e.initiator === 'tokenfield' ) return // Prevent loops
      
      this.setTokens( this.$element.val() )
    }

  , createTokensFromInput: function (e, focus) {
      if (this.$input.val().length < this.options.minLength) return

      var tokensBefore = this.getTokensList()
      this.setTokens( this.$input.val(), true )
      if (tokensBefore == this.getTokensList() && this.$input.val().length) return // No tokens were added, do nothing

      if (this.$input.hasClass('tt-query')) {
        // Typeahead acts weird when simply setting input value to empty,
        // so we set the query to empty instead
        this.$input.typeahead('setQuery', '')
      } else {
        this.$input.val('')
      }

      if (this.$input.data( 'edit' )) {
        this.unedit(focus)
      }

      e.preventDefault()
      e.stopPropagation()
    }  

  , next: function (add) {
      if (add) {
        var firstActive = this.$wrapper.find('.active:first')
          , deactivate = firstActive && this.firstActiveToken ? firstActive.index() < this.firstActiveToken.index() : false

        if (deactivate) return this.deactivate( firstActive )
      }

      var active = this.$wrapper.find('.active:last')
        , next = active.nextAll('.token:first')

      if (!next.length) {
        this.$input.focus()
        return
      }

      this.activate(next, add)
    }

  , prev: function (add) {

      if (add) {
        var lastActive = this.$wrapper.find('.active:last')
          , deactivate = lastActive && this.firstActiveToken ? lastActive.index() > this.firstActiveToken.index() : false

        if (deactivate) return this.deactivate( lastActive )
      }

      var active = this.$wrapper.find('.active:first')
        , prev = active.prevAll('.token:first')

      if (!prev.length) {
        prev = this.$wrapper.find('.token:first')
      }

      if (!prev.length && !add) {
        this.$input.focus()
        return
      }

      this.activate( prev, add )
    }

  , activate: function (token, add, multi, remember) {

      if (!token) return

      if (this.$wrapper.find('.token.active').length === this.$wrapper.find('.token').length) return

      if (typeof remember === 'undefined') var remember = true

      if (multi) var add = true

      this.$copyHelper.focus()

      if (!add) {
        this.$wrapper.find('.active').removeClass('active')
        if (remember) {
          this.firstActiveToken = token 
        } else {
          delete this.firstActiveToken
        }
      }

      if (multi && this.firstActiveToken) {
        // Determine first active token and the current tokens indicies
        // Account for the 1 hidden textarea by subtracting 1 from both
        var i = this.firstActiveToken.index() - 2
          , a = token.index() - 2
          , _self = this

        this.$wrapper.find('.token').slice( Math.min(i, a) + 1, Math.max(i, a) ).each( function() {
          _self.activate( $(this), true )
        })
      }

      token.addClass('active')
      this.$copyHelper.val( this.getTokensList( true ) ).select()
    }

  , activateAll: function() {
      var _self = this

      this.$wrapper.find('.token').each( function (i) {
        _self.activate($(this), i !== 0, false, false)
      })
    }

  , deactivate: function(token) {
      if (!token) return

      token.removeClass('active')
      this.$copyHelper.val( this.getTokensList( true ) ).select()
    }

  , toggle: function(token) {
      if (!token) return

      token.toggleClass('active')
      this.$copyHelper.val( this.getTokensList( true ) ).select()
    }

  , edit: function (token) {
      if (!token) return

      var value = token.data('value')
        , label = token.find('.token-label').text()

      // Allow changing input value before editing
      var beforeEditEvent = $.Event('beforeEditToken')
      beforeEditEvent.token = {
        value: value,
        label: label
      }
      beforeEditEvent.relatedTarget = token.get(0)
      this.$element.trigger( beforeEditEvent )
      
      if (!beforeEditEvent.token) return

      value = beforeEditEvent.token.value
      label = beforeEditEvent.token.label

      token.find('.token-label').text(value)
      var tokenWidth = token.outerWidth()

      var $_input = this.$input.hasClass('tt-query') ? this.$input.parent() : this.$input

      token.replaceWith( $_input )

      this.preventCreateTokens = true

      this.$input.val( value )
                .select()
                .data( 'edit', true )
                .width( tokenWidth )
    }

  , unedit: function (focus) {
      var $_input = this.$input.hasClass('tt-query') ? this.$input.parent() : this.$input
      $_input.appendTo( this.$wrapper )
      
      this.$input.data('edit', false)

      this.update()

      // Because moving the input element around in DOM 
      // will cause it to lose focus, we provide an option
      // to re-focus the input after appending it to the wrapper
      if (focus) {
        var _self = this
        setTimeout(function () {
          _self.$input.focus()
        }, 1)
      }
    }

  , remove: function (e, direction) {
      if (this.$input.is(':focus') || this.disabled) return

      var token = (e.type === 'click') ? $(e.target).closest('.token') : this.$wrapper.find('.token.active')
      
      if (e.type !== 'click') {
        if (!direction) var direction = 'prev'
        this[direction]()
      }

      // Prepare events

      var removeEvent = $.Event('removeToken')
      removeEvent.token = this.getTokenData( token )

      var changeEvent = $.Event('change')
      changeEvent.initiator = 'tokenfield'

      // Remove token from DOM
      token.remove()

      // Trigger events
      this.$element.val( this.getTokensList() ).trigger( removeEvent ).trigger( changeEvent )

      // Focus, if necessary
      if (!this.$wrapper.find('.token').length || e.type === 'click') this.$input.focus()

      // Adjust input width
      this.$input.css('width', this.options.minWidth + 'px')
      this.update()

      e.preventDefault()
      e.stopPropagation()
    }

  , update: function (e) {
      var value = this.$input.val()

      if (this.$input.data('edit')) {

        if (!value) {
          value = this.$input.prop("placeholder")
        }
        if (value === this.$mirror.text()) return

        this.$mirror.text(value)
        
        var mirrorWidth = this.$mirror.width() + 10;
        if ( mirrorWidth > this.$wrapper.width() ) {
          return this.$input.width( this.$wrapper.width() )
        }

        this.$input.width( mirrorWidth )
      }
      else {
        this.$input.css( 'width', this.options.minWidth + 'px' )
        this.$input.width( this.$wrapper.offset().left + this.$wrapper.width() - this.$input.offset().left + 5 )
      }
    }

  , focusInput: function (e) {
      if ($(e.target).closest('.token').length || $(e.target).closest('.token-input').length) return
      // Focus only after the current call stack has cleared,
      // otherwise has no effect.
      // Reason: mousedown is too early - input will lose focus
      // after mousedown. However, since the input may be moved
      // in DOM, there may be no click or mouseup event triggered.
      var _self = this
      setTimeout(function() {
        _self.$input.focus()
      }, 0)
    }

  , search: function () {
      if ( this.$input.data('ui-autocomplete') ) {
        this.$input.autocomplete('search')
      }
    }

  , disable: function () {
      this.disabled = true;
      this.$input.prop('disabled', true);
      this.$element.prop('disabled', true);
      this.$wrapper.addClass('disabled');
    }

  , enable: function () {
      this.disabled = false;
      this.$input.prop('disabled', false);
      this.$element.prop('disabled', false);
      this.$wrapper.removeClass('disabled');
    }

  }


 /* TOKENFIELD PLUGIN DEFINITION
  * ======================== */

  var old = $.fn.tokenfield

  $.fn.tokenfield = function (option, param) {
    var value

    var elements = this.each(function () {
      var $this = $(this)
        , data = $this.data('bs.tokenfield')
        , options = typeof option == 'object' && option

      if (typeof option === 'string' && data && data[option]) {
        value = data[option](param)
        return value;
      } else {
        if (!data) $this.data('bs.tokenfield', (data = new Tokenfield(this, options)))
      }
    })

    return value ? value : elements;
  }

  $.fn.tokenfield.defaults = {
    minWidth: 60,
    minLength: 0,
    allowDuplicates: false,
    autocomplete: {},
    typeahead: {},
    showAutocompleteOnFocus: false,
    createTokensOnBlur: false,
    triggerKeys: [44]
  }

  $.fn.tokenfield.Constructor = Tokenfield


 /* TOKENFIELD NO CONFLICT
  * ================== */

  $.fn.tokenfield.noConflict = function () {
    $.fn.tokenfield = old
    return this
  }

}(window.jQuery);
