/**
 * @license Copyright (c) 2003-2013, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
		config.language = 'zh-cn'; 
	  //config.uiColor = '#9AB8F3'; 
		config.entities = false;
		config.protectedSource.push(/<#--\w*?\-->/g);
	  //自定义工具栏1
	  config.toolbarGroups = [
		                		{ name: 'clipboard',   groups: [ 'clipboard', 'undo' ] },
		                		{ name: 'editing',     groups: [ 'find', 'selection', 'spellchecker' ] },
		                		{ name: 'links' },
		                		{ name: 'insert'},
		                		{ name: 'forms' },
		                		{ name: 'tools' },
		                		{ name: 'mode',	   groups: [ 'mode','doctools'] },
		                		//{ name: 'others' },
		                		{ name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
		                		{ name: 'paragraph',   groups: [ 'list', 'indent', 'blocks', 'align' ] },
		                		{ name: 'styles' },
		                		{ name: 'colors' },
		                		{ name: 'Font' },
		                		{ name: 'FontSize' },
		                		{ name: 'TextColor' },
		                		//{ name: 'about' }
		                	];
	  
	  
	  
	config.font_names = '宋体/宋体;黑体/黑体;仿宋/仿宋_GB2312;楷体/楷体_GB2312;隶书/隶书;幼圆/幼圆;'+ config.font_names ;
	var pathName = window.document.location.pathname;
	var projectName = pathName
			.substring(0, pathName.substr(1).indexOf('/') + 1);
	config.filebrowserUploadUrl = projectName + '/ckUpload.htm';
	config.filebrowserImageUploadUrl = projectName + '/ckUpload.htm'; // 固定路径

	// Remove some buttons provided by the standard plugins, which are
	// not needed in the Standard(s) toolbar.
	config.removeButtons = 'Underline,Subscript,Superscript';

	// Set the most common block elements.
	config.format_tags = 'p;h1;h2;h3;pre';

	// Simplify the dialog windows.
	config.removeDialogTabs = 'image:advanced;link:advanced';
};
