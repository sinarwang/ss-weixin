if(document.domain.lastIndexOf('.')>0){document.base_domain=document.domain;document.domain=getRootDomain()}
function resizeImage(A,B,C,F){var E=new Image();E.src=A.src;var S=1.0,W=0,H=0;if(isIE){if(E.readyState=='complete'){W=E.width;H=E.height}}else(E.complete)
{W=E.width;H=E.height}if(!W){A.style.display='block';A.style.width='auto';W=A.offsetWidth;H=A.offsetHeight}S=W/H;if(W>B){W=B;H=Math.round(W/S);if(C&&H>C){H=C;W=Math.round(H*S)}}if(C&&H>C){H=C;W=Math.round(H*S)}A.style.width=W+'px';if(!F){A.style.height=H+'px';var x=parseInt((B-W)/2),y=parseInt((C-H)/2);A.style.marginTop=y+'px';A.style.marginLeft=x+'px'}return [W,H]}
function resizeImageThumb(A){var I=new Image();I.src=A.src;var S=1.0,W=0,H=0,C=D=90;if(isIE){if(I.readyState=='complete'){W=I.width;H=I.height}}else(I.complete)
{W=I.width;H=I.height}if(!W){A.style.display='block';A.style.width='auto';W=A.offsetWidth;H=A.offsetHeight}S=W/H;if(W>C){W=C;H=Math.round(W/S);if(H>D){H=D;W=Math.round(H*S)}}if(H>D){H=D;W=Math.round(H*S)}if(W==0||H==0){W=C;H=D}A.style.width=W+'px';A.style.height=H+'px';var x =parseInt((90-W)/2);A.parentNode.style.width='auto';A.parentNode.style.height='auto';A.parentNode.parentNode.style.left=x+'px'}

// check is image url
function isImgUrl(s){
	var pic = new RegExp("((?:http|https)://(&(?=amp;)|[A-Za-z0-9\./=\?%_~@#:;\+\-])+(gif|jpg|jpeg|png)+)", "ig");
	var url = new RegExp("((?:http|https)://(&(?=amp;)|[A-Za-z0-9\./=\?%_~@&#:;\+\-])+)","ig");
	if(pic.test(s.trim())){return 2}else if(url.test(s.trim())){return 1}else{return 0}
}

/*
 * Content class
 */
Content = function(a){
	this.grid = $('contentlist');
	this.checkbox = this.grid ? this.grid.getElementsByTagName('input') : null;
	this.channels = null;
};
/*
 * Content class prototype
 */
Content.prototype = {
	edit : function(){
		var A = this.getSelected();
		if(A.length == 0){
			alert('请选择一个您想要编辑的内容');
		}else if(A.length > 1){
			alert('请选择的内容太多无法编辑，请只选择一个内容进行编辑')
		}else{
			this.location({action: 'edit',contentid: A[0]});
		}
	},
	del : function(contentid){
		var A = contentid ? [contentid] : this.getSelected();
		if(A.length == 0){
			alert('请选择您想要删除的内容');
		}else{
			if(!this.deleteWin){
				var html = '<div class="formdl dialog"><h3 style="color:red">您确定要删除您所选的稿件吗？</h3><p class="tip red">提示：此删除操作不可恢复！</p><div class="buttons"><input type="button" value="确定" class="button" onclick="Content.deleteEvent(true, this)" /><input type="button" value="取消" class="button" onclick="Content.deleteWin.hide()" /></div></div>';
				this.deleteWin = Account.window({id:'content-delete', title:'删除稿件', html:html, height:220});
			}
			this.deleteWin.contentid = A;
			if(this.deleteWin.submitBtn) this.deleteWin.submitBtn.disabled = false;
			this.deleteWin.show();
		}
	},
	// submit or cancel delete all favorites
	deleteEvent : function(flag){
		if(flag){
			var contentid = this.deleteWin.contentid.join(',');
			this.location({action: 'delete',contentid: contentid});
		}else{
			this.deleteAllWin.hide();
		}
	},
	getSelected : function(){
		var A = new Array;
		for(var i=1;i<this.checkbox.length;i++){
			if(this.checkbox[i].checked) A.push(this.checkbox[i].value);
		}
		return A;
	},
	select : function(A,B,C){
		for(var i=1;i<this.checkbox.length;i++){
			this.checkbox[i].checked = B == 2 ? !this.checkbox[i].checked : B == 1 ? true : false;
		}
		this.checkbox[0].checked = B == 1 ? true : false;
	},
	submitSearch : function(){
		var k = $('keywords');
		if(!k.value || k.value == '搜索 关键字')return;
		this.location({k:k.value});
	},
	location : function(o){
		var url = window.location.search;
		var theRequest = new Object(); 
		if(url.indexOf("?") != -1){
			var str = url.substr(1); 
			var strs = str.split("&");
			for(var i = 0; i < strs.length; i ++){theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);}
			for (var i in o) {
				if (i == 'remove' || i== 'indexOf') continue;
				theRequest[i] = o[i];
			}
			url = window.location.protocol + '//' + window.location.host + window.location.pathname;
			var spilt_str = '?';
			for(var i in theRequest){
				if (i == 'remove' || i== 'indexOf') continue;
				url += spilt_str+i+'='+theRequest[i];
				spilt_str = '&';
			}
		}else{
			url = window.location.href;
			var spilt_str = '?';
			for (var i in o) {
				if (i == 'remove' || i== 'indexOf') continue;
				url += spilt_str+i+'='+o[i];
				spilt_str = '&';
			}
		}
		window.location.href = url;
	},
	keywordsBlur : function(o){if(o.value=='')o.value='搜索 关键字'},
	keywordsFocus : function(o){if(o.value=='搜索 关键字')o.value=''},
	// new button event show model menu
	showModelMenu : function(o){
		if(!this.modelMenu){
			var lis = $('modellist').getElementsByTagName('li');
			var model = new Array;
			for(var i=0;i<lis.length;i++){
				if(lis[i].attributes['ismodel'] && lis[i].attributes['ismodel'].value == 1){
					var a = lis[i].getElementsByTagName('a')[0];
					model.push([a.innerHTML, a.href]);
				}
			}
			var m = document.createElement('div');
			m.className = 'modelmenu clearfix';
			if(model.length > 2){
				m.className += ' floatmodel';
				var c = Math.ceil(model.length / 10);
				if(c > 7) c = 7;
				m.style.width =  c * 100 + 'px';
			}
			var html = '';
			for(var i=0;i<model.length;i++){
				html += '<a href="' + model[i][1] + '&action=edit">新建' + model[i][0] + '</a>'
			}
			m.innerHTML = html;
			m.el = o;
			m.EventsEls = [m,o];
			m.show = function(){document.clickEl=this;onEvent(document,'click',hideDocClickEventEl);setCls(this.el,'btn-click');this.style.display='block'}
			m.hide = function(){document.clickEl=null;unEvent(document,'click',hideDocClickEventEl);setCls(this.el,'btn-click',1);this.style.display='none'}
			this.modelMenu = m;
			o.parentNode.insertBefore(m, o);		
		}
		this.modelMenu.show();
	},
	// set content thumb
	selectThumb : function(o,config){
		var el = $(o);
		if(!el){
			alert('找不到 Dom： ' + o);
			return ;
		}
		if(!el.callback){
			el.callback = function(data){
				if(typeof(data) == 'object'){
					el.value = data.image;
					$(o+'-value').value = data.resource;
				}else{
					$(o+'-value').value = el.value = data;
				}
			}
		}
		if(!config){
			config = {filetype:'',filesize:0};
		}
		ImageSelector.select({single:true,filetype:config.filetype,filesize:config.filesize,type:'thumb',callback:el.callback});
	},
	//
	previewImage : function(o){
		var el = $(o);
		if(!el){
			alert('找不到 Dom： ' + o);
			return ;
		}
		el.value ? ImageSelector.preview(el.value) : alert('无法预览，请先选择缩略图');
	}
};
/*
 * Channel tree class
 */
ChannelTree = function(input, channelname, modelid, url){
	this.store = new Array;
	this.level = 0;
	this.input = $(input);
	this.dd = document.getElementById('dd_channel');
	this.channelname = channelname;
	this.modelid = modelid||1;
	this.url = url;
	this.loaded = false;
	this.spaceImg = 'themes/default/images/s.gif';
	this.render();
};
ChannelTree.prototype = {
	render : function(){
		this.nameInput = document.createElement('input');
		this.nameInput.setAttribute('type', 'text');
		this.nameInput.className  = 'txt';
		this.nameInput.name  = 'channel';
		this.nameInput.readOnly = true;
		this.nameInput.value = this.channelname;
		if(!this.input){
			if(!this.randSpan) this.randSpan = 0;
			var span = "channelname-input-span-" + this.randSpan;
			this.randSpan++;
			document.write('<span id="' + span + '" style="display:none"></span>');
			span = $(span);
			span.parentNode.appendChild(this.nameInput);
			return ;
		}
		this.input.parentNode.appendChild(this.nameInput);
		this.trigger = document.createElement('span');
		this.trigger.className = 'trigger combotreetrigger';
		this.input.parentNode.appendChild(this.trigger);
		// load data
		this.load();
		// init trigger event
		this.trigger.overCls = 'combotreetrigger-over';
		this.trigger.clickCls = 'combotreetrigger-click';
		this.trigger.onmouseover = function(){setCls(this,this.overCls)}
		this.trigger.onmouseout = function(){setCls(this,this.overCls,true)}
		var tthis = this;
		var cls = this.nameInput.parentNode.className.split(' ');
		tthis.nameInput.parentNode.focusCls = cls.indexOf('rinput') >= 0 ? 'rfocus' : 'focus'; 
		this.nameInput.onfocus = function(){this.blur();if(!tthis.treePanel) tthis.renderTreePanel();tthis.treePanel.show()}
		this.trigger.onclick = function(){if(!tthis.treePanel) tthis.renderTreePanel();tthis.treePanel.show()}
	},
	// render tree panel
	renderTreePanel : function(){
		this.treePanel = document.createElement('div');
		var tthis = this;
		this.treePanel.show = function(){
			setCls(tthis.nameInput.parentNode, tthis.nameInput.parentNode.focusCls);
			setCls(tthis.trigger, tthis.trigger.clickCls);
			document.clickEl = this;
			onEvent(document, 'click', hideDocClickEventEl);
			setVisibile(this, true);
		}
		this.treePanel.hide = function(){
			setCls(tthis.nameInput.parentNode, tthis.nameInput.parentNode.focusCls, true);
			setCls(tthis.trigger, tthis.trigger.clickCls, true);
			setVisibile(this, false);
		};
		this.treePanel.className = 'treepanel combotreetriggerlist';
		insertAfter(this.treePanel, this.input.parentNode);
		this.treePanel.style.width = this.nameInput.offsetWidth + 'px';
		this.treePanel.EventsEls = [this.treePanel, this.trigger, this.nameInput];
		this.treePanel.eventsElDeep = 10;
		tthis.renderTree();
	},
	// load tree data
	load : function(){
		var tthis = this;
		ajax.request('get', this.url, {
			success:function(r){
				var v = ajax.jsondecode(r.responseText);
				if(v.success == 1){
					tthis.loaded = true;
					tthis.store = v.data;
					//tthis.renderTree();
				}else if(v.success == -1){
					Account.login({callback:function(){tthis.load()}});
				}else{
					alert(v.message ? v.message : '请求服务失败，请重试！');
				}
			},
			failure:function(r){tthis.treePanel.innerHTML = '<span style="display:block;color:#C30;padding:10px;">加载数据失败, ' + r.status + '错误</span>'}
		},{action:'getchannel'});
	},
	// render tree
	renderTree : function(){
		if(!this.loaded || this.isTreeRendered) return ;
		this.isTreeRendered = true;
		this.renderNode(this.store, this.treePanel, 0);
	},
	// render tree node
	renderNode : function(children, container, level, parentChannel){
		level = level||0;
		var l = 0;
		var w = 16;
		if(children.length > 0){
			var ul = document.createElement('ul');
			var tthis = this;
			for(var i=0;i<children.length;i++){
				var li = document.createElement('li');
				var div = document.createElement('div');
				div.setAttribute('unselectable', 'on');
				div.isTreeNode = true;
				div.att = children[i];
				div.level = level;
				var bd = document.createElement('p');
				div.appendChild(bd);
				var folder = document.createElement('img');
				folder.src = this.spaceImg;
				var pl = l + level * w + 4;
				div.hasChild = children[i].children  && children[i].children.length;
				if(div.hasChild){
					var arrow = document.createElement('img');
					arrow.src = this.spaceImg;
					arrow.isarrow = true;
					arrow.className = 'arrow';
					arrow.onclick = function(){tthis.expand(this.parentNode.parentNode)};
					arrow.onmouseover = function(){setCls(this, this.parentNode.parentNode.expanded?'arrow-expanded-over':'arrow-over')}
					arrow.onmouseout = function(){setCls(this, this.parentNode.parentNode.expanded?'arrow-expanded-over':'arrow-over',true)}
					bd.appendChild(arrow);
					div.arrow = arrow;
					div.setAttribute('title','双击展开');
					div.ondblclick = function(){tthis.expand(this)};
				}else{
					pl += w;
				}
				div.folder = folder;
				var modeltype = document.createElement('span');
				
				modeltype.className = 'modeltype';
				if(children[i].cross){
					folder.setAttribute('title', parentChannel.att.modelname);
					folder.className = parentChannel.folder.className;
					var modeltype_txt = parentChannel.att.modelname + ', 交叉';
				}else{
					folder.setAttribute('title', children[i].modelname);
					folder.className = children[i].cls;
					var modeltype_txt = children[i].modelname;
					if(children[i].type == 1) modeltype_txt += '封面';
					if(children[i].hascross == 1) modeltype_txt += ', 含交叉';
				}
				modeltype.innerHTML = modeltype_txt;
				
				div.style.paddingLeft = pl + 'px';
				bd.appendChild(folder);
				var span = document.createElement('span');
				div.onselectstart = span.onselectstart = function(){return false}
				span.innerHTML = children[i].text;
				bd.appendChild(span);
				bd.appendChild(modeltype);
				//div.onclick=function(){selectNode(this)};
				div.onclick = function(e){tthis.select(this,e)}
				div.onmouseover = function(){setCls(this,this.att.contribute&&this.att.modelid==tthis.modelid?'over':'unselectable')}
				div.onmouseout = function(){setCls(this,'over',true);setCls(this,'unselectable',true)}
				li.appendChild(div);
				ul.appendChild(li);
			}
			container.appendChild(ul);
			return ul;
		}
	},
	// expand tree node
	expand : function(o){
		if(o.hasChild && !o.childEl) o.childEl = this.renderNode(o.att.children, o.parentNode, o.level + 1, o);
		if(o.expanded){
			o.expanded = false;
			o.childEl.style.display = 'none';
			setCls(o.arrow,'arrow-expanded-over',true);
			setCls(o.arrow,'arrow-expanded',true);
			setCls(o.folder,'folder-expanded',true);
		}else{
			o.expanded = true;
			o.childEl.style.display = 'block';
			setCls(o.arrow,'arrow-over',true);
			setCls(o.arrow,'arrow-expanded');
			setCls(o.folder,'folder-expanded');
		}
		this.treePanel.style.height = '160px';
		var h = Math.max(this.treePanel.scrollHeight, 160);
		h = Math.min(300, h)
		this.treePanel.style.height = h + 'px';
	},
	// select tree node
	select : function(o,e){
		e = window.event||e;
		var target = window.event ? window.event.srcElement : e.target;
		if(target.isarrow) return ;
		if(o.att.contribute && o.att.modelid == this.modelid){
			this.nameInput.value = o.att.cross ? o.parentNode.parentNode.parentNode.childNodes[0].att.text + '[' + o.att.text + ']' : o.att.text;
			this.input.value = o.att.id;
			if(this.treePanel.selected) setCls(this.treePanel.selected, 'selected', true);
			this.treePanel.selected = o;
			setCls(o, 'selected');
			this.treePanel.hide();
		}
	}
};

/*
 * Image upload and select
 */
ImageSelector = {
	images : 0,
	maxImages : 20,
	urlImages : new Array,
	uploadImages : new Array,
	file_size_limit : 1024 * 4,
	single : true,
	selected : null,
	previewMaxWidth : 800,
	previewMaxHeight : 500,
	filetype:'*.jpg;*.jpeg;*.png;*.gif',
	// select
	select : function(config){
		if(!this.win) this.render();
		this.unselectAll();
		this.form.reset();
		this.callback = config.callback || null;
		this.single = config.single || false;
		this.imageType = config.type || null;
		this.filetype = config.filetype;
		this.file_size_limit = config.filesize || 1024 * 4;
		this.win.show();
	},
	//设置文件大小
    setFileSize : function(filesize){
    	this.filesize = parseInt(filesize) || 1024 * 4;	//上传文件大小
    	if(this.swfupload){
			this.swfupload.setFileSizeLimit(this.filesize);
		}
    },
	//设置文件类型
    setFileType : function(filetype){
    	if(filetype && typeof filetype == 'string'){		//上传文件类型
			filetype = filetype.split(',');
		}else if(typeof filetype == 'array'){
			
		}else{
			filetype = new Array('jpg','jpeg','png','gif');
		}
		var _types = [];
		for(var i=0,len=filetype.length;i<len;i++){
			filetype[i] = filetype[i].trim().toLowerCase();
			if(filetype[i]) _types.push('*.'+filetype[i]);
		}
		if(_types.length){
			filetype = _types.join(';');
		}else{
			filetype = '*.*';
		}
		if(this.swfupload){
			this.swfupload.setFileTypes(filetype,'All Files ('+filetype+')');
		}
    },
	formatSizes : function(s){if(s==''||isNaN(s)){return s}if(s>=1073741824){s=Math.round(s/1073741824*100)/100+' GB'}else if(s>=1048576){s=Math.round(s/1048576*100)/100+' MB'}else if(s>=1024){s=Math.round(s/1024*100)/100+' KB'}else{s+=' Bytes'}return s},

	// render win
	render:function(o){
		var tthis = this;
		var html = '<form class="formdl"><div class="imgselector clearfix"><ul class="tab"><li class="active">我的电脑</li><li>网上图片</li></ul><div><p id="imageselector-mask" class=" tip imageselector-mask"></p><p class="rinput resourcetrigger-swfupload"><input type="text" class="txt resourceinput" readonly="readonly" onfocus="blur()" /><span class="trigger resourcetrigger">浏览...</span><span id="content-image-swfupload-btn"></span></p><p class="tip">提示：选择的图片文件大小不超过4M，支持jpg、jpeg、gif、png</p></div><div><p class="tip">请填写您要插入的网上图片的URL</p><p class="rinput"><input type="text" class="txt resourceinput" value="" name="urlimage" /><span class="trigger resourcetrigger" onclick="ImageSelector.addUrlImage(this)" onmouseover="setCls(this,\'resourcetrigger-over\')" onmouseout="setCls(this,\'resourcetrigger-over\',1);setCls(this,\'resourcetrigger-click\',1)" onmousedown="setCls(this,\'resourcetrigger-click\')" onmouseup="setCls(this,\'resourcetrigger-click\',1)">添加</span></div></div><div class="imgselector-list"><p class="tip">可插入的图片(双击预览图片)</p><ul id="imgselector-img-ul"></ul></div><div class="buttons"><input type="button" value="插入" class="button" id="editor-insertimg-ok" /><input type="button" value="取消" class="button" id="editor-insertimg-cancel" /></div></form>';
		this.win = Account.window({id:'content-editor-insert-image', title:'插入图片', html:html, width:670});
		this.form = this.win.body.getElementsByTagName('form')[0];
		this.form.urlimage.value = this.form.urlimage.defaultValue = 'http://';
		onEvent(this.form.urlimage, 'focus', function(){if(this.value.trim()==this.defaultValue)this.value=''})
		onEvent(this.form.urlimage, 'blur', function(){if(this.value.trim()=='')this.value=this.defaultValue})
		this.initUploader();
		this.listCt = $('imgselector-img-ul');
		var tab = this.win.body.getElementsByTagName('ul')[0];
		var div = tab.parentNode.getElementsByTagName('div');
		var li = tab.getElementsByTagName('li');
		li[0].index = 0;
		li[1].index = 1;
		this.win.active = function(i){
			li[i].className='active';
			li[i==0?1:0].className='';
			div[i==0?1:0].style.display='none';
			div[i].style.display='block'
		}
		li[0].onclick = li[1].onclick = function(){tthis.win.active(this.index)}
		this.win.active(0);
		this.addImageCt(this.maxImages);
		var buttons = this.win.body.getElementsByTagName('input');
		buttons[buttons.length - 2].onclick = function(){tthis.insert()}
		buttons[buttons.length - 1].onclick = function(){tthis.win.hide()}
	},
	// set upload tip
	setUploadTip:function(s){
		if(!this.uploadTipEl){
			this.uploadTipEl = $('imageselector-mask');
			this.uploadTipEl.show = show;
			this.uploadTipEl.hide = hide;
		}
		this.uploadTipEl.innerHTML = s;
	},
	// initial swf upload
	initUploader:function(){
		var _this = this;
		this.swfupload = new SWFUpload({
			flash_url : flash_url,
			flash9_url : flash9_url,
			upload_url: window.location.href,
			post_params:{session_id:swfupload_session_id, action:'uploadimage',ajax:1},
			file_post_name : 'IMAGE',
			file_size_limit : this.file_size_limit,
			file_types : this.filetype,
			file_types_description : "All Files",
			file_upload_limit : 0,
			file_queue_limit : 0,
			swfupload_preload_handler : function(){_this.setUploadTip('<font color=red>正在加载 swfupload 上传组件，请稍候...</font>');},
			swfupload_loaded_handler : function(){_this.setUploadTip('请点击“浏览...”按钮从您的电脑里选择您要插入的图片');_this.setFileType(_this.filetype);_this.setFileSize(_this.file_size_limit);},
			swfupload_load_failed_handler : function(m){alert('请升级浏览器Flash Player版本至9.0以上');},
			file_dialog_start_handler : function(){},
			file_queued_handler : function(f){_this.addUploadImage(f)},
			file_queue_error_handler : function(f,e,m){alert('上传的图片太大，请重新选择(不能超过'+_this.formatSizes(_this.file_size_limit * 1024)+')')},
			file_dialog_complete_handler : function(ns,nq,t){},
			upload_start_handler : function(f){},
			upload_progress_handler : function(f,c,t){
				var p = ((c/t)*100).toFixed(2);
				var li = _this.uploadImages[f.id].li;
				li.p = li.percent.style.width = p + '%';
				//SWFUpload.speed.formatBPS(f.currentSpeed)
			},
			upload_error_handler : function(f,e,m){alert(m)},
			upload_success_handler : function(f,d,r){
				var v = ajax.jsondecode(d);
				aa = d;
				if(v.success == 1){
					v.fileid = f.id;
					_this.addImage(v);
				}else{
					if(v.success == 0){
						alert(v.message)
					}else if(v.success == -1){
						alert('登录超时，请重新登录');
					}else{
						alert('PHP的设置的内存太小,请修改php.ini中的memory_limit值,一般设置大小为实际为内存大小的四分之一');
					}
					var li = _this.uploadImages[f.id].li;
					li.delBtn.style.display = 'none';
					li.removeChild(li.upload);
					_this.swfupload.cancelUpload(f.id);
					delete(_this.uploadImages[f.id]);
				}
			},
			upload_complete_handler : function(f){},
			// Button Settings
			button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,
			button_placeholder_id : 'content-image-swfupload-btn',
			button_width: 52,
			button_height: 24,
			custom_settings:{},
			debug: false
		});
	},
	// add a image contariner
	addImageCt : function(n){
		var tthis = this;
		for(var i=0;i<n;i++){
			var li = document.createElement('li');
			li.delBtn = document.createElement('a');
			li.delBtn.setAttribute('href', 'javascript:void(0)');
			li.delBtn.setAttribute('title', '删除');
			li.delBtn.onclick = function(){tthis.deleteImage(this)}
			li.appendChild(li.delBtn);
			li.div = document.createElement('div');
			li.div.className = 'imgselector-list-ct'
			li.appendChild(li.div);
			li.ct = document.createElement('em');
			li.div.appendChild(li.ct);
			li.onclick = function(){
				if(!this.haveImg) return;
				this.selected ? this.unselect() : this.select()
			}
			li.ondblclick = function(){
				if(this.haveImg){
					var img = this.img;
					var src = img.src;
					if(img.images){if(img.images.m)src = img.images.m;else if(img.images.l)src = img.images.l;else if(img.images.o)src = img.images.o;}
					tthis.preview(src);
				}
			}
			li.select = function(){
				if(!this.haveImg || this.selected || (tthis.single && tthis.selected && tthis.selected == this)) return;
				if(tthis.single){
					if(tthis.selected) tthis.selected.unselect();
					tthis.selected = this;
				}
				if(!this.selected){
					this.selected = true;
					setCls(this, 'selected');
					this.setAttribute('title', '取消选择');
				}
			}
			li.unselect = function(){
				if(this.haveImg && this.selected){
					if(tthis.selected = this) tthis.selected = null;
					this.selected = false;
					setCls(this, 'selected', true);
					this.setAttribute('title', '选择');
				}
			}
			this.listCt.appendChild(li);
		}
	},
	// unselect images
	unselectAll : function(){
		for(var i=0;i<this.images;i++) this.listCt.childNodes[i].unselect();
	},
	// add upload image
	addUploadImage : function(file){
		if(this.images == (this.maxImages - 1)){
			alert('最多只能添加 ' + this.maxImages + ' 张图片');
			this.swfupload.cancelUpload(file.id);
			return ;
		}
		this.swfupload.startUpload(file.id);
		var li = this.listCt.childNodes[this.images];
		file.li = li;
		this.uploadImages[file.id] = file;
		li.delBtn.style.display = 'block';
		li.upload = document.createElement('div');
		li.upload.className = 'imgselector-list-upload';
		li.upload.setAttribute('title', '正在上传：' + file.name + '(' + SWFUpload.speed.formatBytes(file.size) + ')');
		li.p = document.createElement('p');
		li.p.innerHTML = '0%';
		li.upload.appendChild(li.p);
		var div = document.createElement('div');
		li.percent = document.createElement('span');
		div.appendChild(li.percent);
		li.upload.appendChild(div);
		li.appendChild(li.upload);
	},
	// add a url image
	addUrlImage : function(o){
		if(this.images == (this.maxImages - 1)){
			alert('最多只能添加 ' + this.maxImages + ' 张图片');
			return ;
		}
		var url = this.form.urlimage.value.trim();
		var isUrl = isImgUrl(url)
		if(isUrl > 0){
			if(isUrl == 1 && !confirm("图片URL: " + url + "\n可能不是正确的图片URL，确定添加此图片URL吗？")) return;
			this.form.urlimage.value = this.form.urlimage.defaultValue;
			if(this.urlImages.length && this.urlImages.indexOf(url) >= 0){
				alert('网址图片已经添加');
				return ;
			}
			this.addImage(url);
		}else{
			alert("错误的URL: \n" + url);
		}
	},
	// add image into image list
	addImage : function(data){
		var li = typeof data == 'string' ? this.listCt.childNodes[this.images] : this.uploadImages[data.fileid].li;
		li.img = document.createElement('img');
		li.img.onload = function(){resizeImage(this,48,48)}
		li.img.onerror = function(){this.parentNode.innerHTML='图片错误'}
		li.haveImg = true;
		li.ct.appendChild(li.img);
		li.setAttribute('title', '选择');
		this.images++;
		if(typeof data == 'string'){
			li.img.src = data;
			li.isurl = true;
			li.delBtn.style.display = 'block';
			this.urlImages.push(data);
		}else{
			li.img.src = data.images.t;
			li.img.resource = data.resource;
			li.img.fileid = data.fileid;
			li.img.images = data.images;
			this.uploadImages[data.fileid].uploaded = true;
			this.uploadImages[data.fileid].images = data.images;
			this.uploadImages[data.fileid].resource = data.resource;
			li.removeChild(li.upload);
		}
	},
	// delte image
	deleteImage : function(o){
		this.images--;
		var li = o.parentNode;
		if(li.isurl){
			this.urlImages.splice(this.urlImages.indexOf(li.img.src), 1);
		}else{
			this.swfupload.cancelUpload(li.fileid);
			delete(this.uploadImages[li.fileid]);
		}
		this.listCt.removeChild(li);
		this.addImageCt(1);
	},
	// insert selected image
	insert : function(){
		var data = new Array;
		for(var i=0;i<this.images;i++){
			var li = this.listCt.childNodes[i];
			if (li.selected) {
				data.push(li.isurl ? li.img.src : {id:li.img.fileid,images:this.uploadImages[li.img.fileid].images,resource:this.uploadImages[li.img.fileid].resource});
			}
		}
		if(data.length){
			switch(this.imageType){
				case 'thumb' :
					if(typeof(data[0]) == 'object') {
						data = {image:data[0].images.t,resource:data[0].resource};
					}else{
						data = data[0];
					}
					break;
			}
			if(this.callback && typeof this.callback == 'function') this.callback(data);
			this.win.hide();
		}else{
			alert('请选择要插入的图片');
		}
	},
	// preview
	preview : function(src){
		if(!this.imagePreviewer){
			var tthis = this;
			this.imagePreviewer = document.createElement('div');
			this.imagePreviewer.className = 'image-previewer';
			this.imagePreviewer.error = document.createElement('span');
			this.imagePreviewer.appendChild(this.imagePreviewer.error);
			this.imagePreviewer.img = document.createElement('img');
			this.imagePreviewer.img.show = this.imagePreviewer.error.show = show;
			this.imagePreviewer.img.hide = this.imagePreviewer.error.hide = hide;
			this.imagePreviewer.setAttribute('title', '点击关闭预览');
			this.imagePreviewer.img.setAttribute('title', '点击关闭预览');
			this.imagePreviewer.appendChild(this.imagePreviewer.img);
			this.imagePreviewer.mask = document.createElement('div');
			this.imagePreviewer.mask.className = 'image-previewer-mask';
			this.imagePreviewer.setImage = function(src){
				this.img.hide();
				this.show();
				this.error.show();
				this.error.innerHTML = '图片加载中...';
				this.img.src = src;
				if (typeof src == 'string') {
                    this.img.src = src.indexOf('resource:') < 0 ? src : 'content.php?action=Preview&resource=' + src
                }
				if(document.readyState == 'complete'){
					if(this.img.style.display == 'block') return ;
					this.img.show();
					this.error.hide();
					this.show(resizeImage(this.img, this.previewMaxWidth, this.previewMaxHeight, true));
				}
				//this.style.width = '400px';
				//this.style.height = '250px';
			}
			this.imagePreviewer.show = function(wh){
				if(!wh){
					wh = [400, 250];
				}
				var W = wh[0], H = wh[1];
				this.style.width = this.mask.style.width = W + 'px';
				this.style.height = this.mask.style.height = H + 'px';
				var T = Math.floor((document.documentElement.clientHeight - H) / 2);
				if(T < 0){T=0}
				if(getIEVer() == 6){
					var L = Math.floor((document.body.scrollWidth - W) / 2);
					T += document.documentElement.scrollTop;
				}else{
					var L = Math.floor((document.documentElement.scrollWidth - W) / 2);
				}
				this.style.left = L + 'px';
				this.style.top = T + 'px' ;
				this.mask.style.left = L - 16 + 'px';
				this.mask.style.top = T - 16 + 'px' ;
			}
			this.imagePreviewer.img.onload = function(){
				if(this.style.display == 'block') return ;
				this.show();
				this.parentNode.error.hide();
				this.parentNode.show(resizeImage(this, tthis.previewMaxWidth, tthis.previewMaxHeight, true));
			}
			this.imagePreviewer.img.onerror = function(){
				this.parentNode.error.innerHTML = '无法预览图片';
				this.parentNode.show();
			}
			this.imagePreviewer.onclick = function(){
				setVisibile(this, false);
				setVisibile(this.mask, false);
			}
			document.body.appendChild(this.imagePreviewer.mask);
			document.body.appendChild(this.imagePreviewer);
		}
		this.imagePreviewer.setImage(src);
		setVisibile(this.imagePreviewer, true);
		setVisibile(this.imagePreviewer.mask, true);
	}	
};

/**
 * ImagesField class
 */
var ImagesField = function(input){
	this.index = 0;
	this.defaultHieght = 240;
	this.images = new Array;
	this.uploadImages = new Array;
	this.urlImages = new Array;
	this.selected = new Array;
	this.init(input);
};
/**
 * ImagesField class prototype
 */
ImagesField.prototype = {
	init : function(input){
		this.input = $(input);
		if(!this.input) return ;
		this.render();
		if(this.input.value != ''){
			var json = ajax.jsondecode(this.input.value);
			if(json && json.length){
				this.callback(json);
			}
		}
	},
	// render 
	render : function(){
		this.container = document.createElement('div');
		this.container.className = 'img-cnt-editor clearfix';
		this.toolbar = document.createElement('div');
		this.container.appendChild(this.toolbar);
		this.toolbar.className = 'img-cnt-toolbar';
		var tthis = this;
		this.createButton('添加', function(){tthis.add()});
		this.createButton('编辑', function(){tthis.edit()});
		this.createButton('删除', function(){tthis.del()});
		this.createButton('预览', function(){tthis.preview()});
		var tip = document.createElement('span');
		tip.className = 'fr';
		tip.innerHTML = '支持使用 Ctrl, Shift 键选择多个图片';
		this.toolbar.appendChild(tip);
		this.panel = document.createElement('div');
		this.panel.style.height = this.defaultHieght + 'px';
		this.panel.className = 'img-cnt-body clearfix';
		this.container.appendChild(this.panel);
		this.input.parentNode.appendChild(this.container);
		this.panel.onselectstart = function(){return false}
	},
	// create event button
	createButton : function(name, fn){
		var button = document.createElement('span');
		button.innerHTML = name;
		button.className = 'img-cnt-button';
		button.overCls = 'img-cnt-button-over';
		button.clickCls = 'img-cnt-button-click';
		button.onmouseover = function(){setCls(this, this.overCls)}
		button.onmouseout = function(){setCls(this, this.overCls, 1);setCls(this, this.clickCls, 1)}
		button.onmousedown = function(){setCls(this, this.clickCls)}
		button.onmouseup = function(){setCls(this, this.clickCls, 1)}
		var tthis = this;
		if(fn) button.onclick = fn;
		this.toolbar.appendChild(button);
	},
	// add images
	add : function(){
		var tthis = this;
		ImageSelector.select({callback:function(data){tthis.callback(data)}});
	},
	// callback
	callback : function(data){
		for(var i=0;i<data.length;i++){
			if(typeof data[i] == 'string'){
				if(this.urlImages.indexOf(data[i]) >= 0){continue;}
				this.urlImages.push(data[i]);
				this.images[this.index] = {index:this.index, isurl:true, src:data[i], name:'', href:'', description:'', sort:50};
			}else if(data[i].id){
				if(this.uploadImages.indexOf(data[i].id) >= 0){continue;}
				this.uploadImages.push(data[i].id);
				this.images[this.index] = {index:this.index, id:data[i].id, src:data[i].images.t,images:data[i].images, name:'', resource:data[i].resource, href:'', description:'', sort:50};
			}else{
				this.urlImages.push(data[i]);
				this.images[this.index] = {index:this.index, isurl:true, src:data[i].src};
				this.images[this.index].resource = data[i].resource || '';
				this.images[this.index].name = data[i].name || '';
				this.images[this.index].href = data[i].href || '';
				this.images[this.index].description = data[i].description || '';
				this.images[this.index].sort = isNaN(data[i].sort) ? 50 : data[i].sort;
			}
			this.renderImg(this.images[this.index]);
			this.index++;
		}
		if(data.length){
			var h = Math.ceil(this.images.length / 6);
			h = h < 3 ? 240 : h * 120;
			this.panel.style.height = h + 'px';
			this.setInputValue();
		}
	},
	// delete image
	del : function(flag){
		if(this.selected.length == 0){
			alert('请先选择要删除的图片');
			return ;
		}
		while(this.selected.length){
			var div = this.selected[this.selected.length - 1];
			if(flag){
				this.panel.removeChild(div);
			}else{
				var index = div.index;
				if(this.selected.isurl){
					this.urlImages.splice(this.urlImages.indexOf(this.images[index].src), 1);
				}else{
					this.uploadImages.splice(this.uploadImages.indexOf(this.images[index].id), 1);
				}
				delete(this.images[index]);
				this.panel.removeChild(div);
			};
			this.selected.pop();
		}
		if(!flag) this.setInputValue();
	},
	// preview image
	preview : function(){
		if(this.selected.length == 0){
			alert('请选择要预览图片');
		}else if(this.selected.length > 1){
			alert('请只选择一张图片预览');
		}else{
			var img = this.images[this.selected[0].index];
			var src = img.src;
			if(img.images){if(img.images.m)src = img.images.m;else if(img.images.l)src = img.images.l;else if(img.images.o)src = img.images.o;}
			ImageSelector.preview(src);
		}
	},
	// render a image
	renderImg : function(file, index){
		var tthis = this;
		var img = document.createElement('div');
		img.className = 'img-cnt-i-wrap';
		img.index = file.index;
		img.setAttribute('title', '双击编辑图片属性');
		img.onmouseover = function(){setCls(this, 'img-cnt-i-wrap-over')}
		img.onmouseout = function(){setCls(this, 'img-cnt-i-wrap-over', 1)}
		img.onmousedown = function(e){
			e = window.event||e;
			if(e.shiftKey){
				if(!tthis.lastIndex) tthis.lastIndex = tthis.panel.childNodes[0];
				for(var i=0;i<tthis.selected.length;i++) setCls(tthis.selected[i], 'img-cnt-i-wrap-selected', 1);
				tthis.selected = new Array;
				var start = end = 0;
				for(var i=0;i<tthis.panel.childNodes.length;i++){
					if(tthis.panel.childNodes[i] == tthis.lastIndex) start = i;
					if(tthis.panel.childNodes[i] == this) end = i;
				}
				if(start > end){
					var start1 = start;
					start = end;
					end = start1;
				}
				for(var i=start;i<=end;i++){
					var n = tthis.panel.childNodes[i];
					n.selected = true;
					setCls(n, 'img-cnt-i-wrap-selected');
					tthis.selected.push(n);
				}
			}else if(e.ctrlKey){
				setCls(this, 'img-cnt-i-wrap-over', 1);
				setCls(this, 'img-cnt-i-wrap-selected', this.selected);
				tthis.lastIndex = this.selected ? null : this;
				this.selected ? tthis.selected.splice(tthis.selected.indexOf(this), 1) : tthis.selected.push(this);
				this.selected = !this.selected;
			}else{
				for(var i=0;i<tthis.selected.length;i++) setCls(tthis.selected[i], 'img-cnt-i-wrap-selected', 1);
				tthis.selected = new Array;
				setCls(this, 'img-cnt-i-wrap-selected');
				tthis.lastIndex = this;
				this.selected = true;
				tthis.selected.push(this);
			}
		}
		img.ondblclick = function(){
			for(var i=0;i<tthis.selected.length;i++) setCls(tthis.selected[i], 'img-cnt-i-wrap-selected', 1);
			tthis.selected = new Array;
			setCls(this, 'img-cnt-i-wrap-selected');
			tthis.lastIndex = this;
			this.selected = true;
			tthis.selected.push(this);
			tthis.edit(this)
		}
		img.innerHTML = '<div class="img-cnt-i-thumb"><div><p><img src="' + file.src + '" onload="resizeImageThumb(this)" onerror="this.parentNode.innerHTML=\'图片错误\'"></p></div></div><span>' + file.name + '</span>';
		img.name = img.getElementsByTagName('span')[0];
		img.setName = function(n){this.name.title=n;this.name.innerHTML = n.ellipse(12)}
		index ? this.panel.insertBefore(img, this.panel.childNodes[index - 1]) : this.panel.appendChild(img);
		return img;
	},
	edit : function(img){
		if(!img){
			if(this.selected.length == 0){
				alert('请选择要编辑的图片');
				return ;
			}else if(this.selected.length > 1){
				alert('请只选择一张图片进行编辑');
				return ;
			}
			img = this.selected[0];
		}
		if(!this.editor){
			this.editor = document.createElement('div');
			this.editor.show = show;
			this.editor.hide = hide;
			this.editor.className = 'img-infoeditor';
			if(!this.editor.indexnum) this.editor.indexnum = 1;
			this.editor.innerHTML = '<form class="formdl clearfix"><p class="tip"><label for="img-infoeditor-name-'+this.editor.indexnum+'">图片名：</label></p><p class="rinput"><input type="text" class="txt" name="name" id="img-infoeditor-title-'+this.editor.indexnum+'" /></p><p class="tip"><label for="img-infoeditor-href-'+this.editor.indexnum+'">图片超链接：</label></p><p class="rinput"><input type="text" class="txt" name="href" id="img-infoeditor-href-'+this.editor.indexnum+'" /></p><p class="tip"><label for="img-infoeditor-desc-'+this.editor.indexnum+'">图片描述：</label></p><p class="input"><textarea class="txt" name="description" id="img-infoeditor-desc-'+this.editor.indexnum+'"></textarea></p><p class="tip sort-tip"><label for="img-infoeditor-sort-'+this.editor.indexnum+'">排序：</label></p><p class="rinput sort-input"><input type="text" class="txt" name="sort" id="img-infoeditor-sort-'+this.editor.indexnum+'" onkeyup="this.value=this.value.replace(/\\D/g,\'\')" maxlength="4" onbeforepaste="clipboardData.setData(\'text\',clipboardData.getData(\'text\').replace(/\\D/g,\'\'))" /></p></div><div class="operatebtn"><a class="btn" onmouseover="setCls(this,\'btn-hover\')" onmouseout="setCls(this,\'btn-hover\',1);setCls(this,\'btn-click\',1)" onmousedown="setCls(this,\'btn-click\')" onmouseup="setCls(this,\'btn-click\',1)"><span>确定</span></a><a class="btn" onmouseover="setCls(this,\'btn-hover\')" onmouseout="setCls(this,\'btn-hover\',1);setCls(this,\'btn-click\',1)" onmousedown="setCls(this,\'btn-click\')" onmouseup="setCls(this,\'btn-click\',1)"><span>取消</span></a></form>';
			document.body.appendChild(this.editor);
			FormPanel.renderInput(this.editor);
			this.editor.form = this.editor.getElementsByTagName('form')[0];
			var btns = this.editor.getElementsByTagName('a');
			var tthis = this;
			btns[0].onclick = function(){tthis.setValue()}
			btns[1].onclick = function(){tthis.editor.hide()}
			this.editor.indexnum++;
		}
		this.editor.show();
		if(this.editor.img == img) return ;
		this.editor.img = img;
		this.editor.form.name.value = this.images[img.index].name;
		this.editor.form.href.value = this.images[img.index].href;
		this.editor.form.sort.value = this.images[img.index].sort;
		this.editor.form.description.value = this.images[img.index].description;
		var e = img;
		var x = e.offsetLeft, y = e.offsetTop;
		while(e = e.offsetParent){
			x += e.offsetLeft;
			y += e.offsetTop;
		}
		x += 80;
		var dsw = document.documentElement.scrollWidth;
		if((x + this.editor.offsetWidth + (dsw - document.body.offsetWidth) / 2) > dsw) x = x - this.editor.offsetWidth - 60;
		this.editor.style.left = x + 'px';
		this.editor.style.top = y + 80 + 'px';
	},
	setValue : function(){
		var index = this.editor.img.index;
		var old_sort = this.images[index].sort;
		var sort = this.editor.form.sort.value.trim(), name = this.editor.form.name.value.trim(), href = this.editor.form.href.value.trim(), description = this.editor.form.description.value.trim();
		if(old_sort == sort && this.images[index].name == name && this.images[index].href == href && this.images[index].description == description) return ;
		this.images[index].name = name;
		this.editor.img.setName(name);
		this.images[index].href = href;
		this.images[index].description = description;
		this.images[index].sort = sort;
		this.editor.hide();
		if(sort != old_sort){
			var flag = false;
			for(var i=0;i<this.panel.childNodes.length;i++){
				if(this.images[this.panel.childNodes[i].index].sort < sort){
					flag = true;
					break;
				}
			}
			if(!flag && this.panel.childNodes[this.panel.childNodes.length - 1] == this.editor.img) return ;
			this.del(true);
			var div = this.renderImg(this.images[index], flag ? i+1 : 0);
			setCls(div, 'img-cnt-i-wrap-selected');
			this.lastIndex = div;
			div.selected = true;
			this.selected.push(div);
		}
		this.setInputValue();
	},
	setInputValue : function(){
		var data = new Array;
		for(var i=0;i<this.panel.childNodes.length;i++){
			var img = this.images[this.panel.childNodes[i].index];
			data.push({name:img.name, resource:img.resource, src:img.src, href:img.href, description:img.description, sort:img.sort});
		}
		this.input.value = this.panel.childNodes.length ? ajax.jsonencode(data) : '';
	}
};