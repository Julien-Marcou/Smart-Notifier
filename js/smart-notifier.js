"use strict";

function Notifier() {

	var self = this;
	var exec = require('cordova/exec');
	var srcRoot = 'source-notifier';
	var defaultTitle = 'Untitled';
	var defaultMessage = 'No description';
	var icons = {
		'default' : srcRoot.'/default-icon.png'/*,
		'error'   : srcRoot.'/icon-error.png',
		'success' : srcRoot.'/icon-success.png',*/
	};

	self.notify = function(title, message, icon) {
		if(typeof title === 'undefined') {
			title = defaultTitle;
		}
		if(typeof message === 'undefined') {
			message = defaultMessage;
		}
		if(typeof icon === 'undefined') {
			icon = icons.default;
		}
		exec(notifySuccess, notifyError, "Notifier", "notify", [title, message, icon]);
	};

	self.notifySuccess = function(info) {};

	self.notifyError = function(error) {};

}

module.exports = new Notifier();
