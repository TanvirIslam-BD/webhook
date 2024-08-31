<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Webhook</title>
    <asset:javascript src="jquery.js" />
    <asset:stylesheet src="materialdesignicons.min.css" />
    <asset:stylesheet src="vendor.bundle.base.css" />
    <asset:stylesheet src="flag-icon.min.css" />
    <asset:stylesheet src="jquery-jvectormap.css" />
    <asset:stylesheet src="style.css" />
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>
</head>
<body>
<div class="body-wrapper">
    <!-- partial:partials/_sidebar.html -->
    <aside class="mdc-drawer mdc-drawer--dismissible mdc-drawer--open">
        <div class="mdc-drawer__header">
            <a href="/" class="brand-logo">
                <asset:image src="logo.png" />
            </a>
        </div>
        <div class="mdc-drawer__content">
            <div class="user-info">
                Welcome <g:link controller="user" action="index" class = "btn btn-primary btn-lg user-name"><sec:loggedInUserInfo field="username"/></g:link>
            </div>
            <div class="mdc-list-group">
                <nav class="mdc-list mdc-drawer-menu">
                    <div class="mdc-list-item mdc-drawer-item">
                        <g:link class="${controllerName.equals("app")?"active ":""}mdc-drawer-link" controller="app" action="index" elementId="app-link">
                            <i class="material-icons mdc-list-item__start-detail mdc-drawer-item-icon" aria-hidden="true">home</i>
                            Clients
                        </g:link>
                    </div>
                </nav>
            </div>
            <div class="profile-actions">
                <g:link controller="setting" action="index"  elementId="settings-link">Settings</g:link>
                <g:link controller="integration" action="view"  elementId="instance-link">Instances</g:link>
                <span class="divider"></span>
                <g:link controller="logout"  elementId="logout-link">Logout</g:link>
            </div>

        </div>
    </aside>
    <!-- partial -->
    <div class="main-wrapper mdc-drawer-app-content">
        <header class="mdc-top-app-bar">
            <div class="mdc-top-app-bar__row">
                <div class="mdc-top-app-bar__section mdc-top-app-bar__section--align-start">
                    <button class="material-icons mdc-top-app-bar__navigation-icon mdc-icon-button sidebar-toggler">menu</button>
                </div>

            </div>
            <g:if test="${flash !=null && flash.message != null}">
                <div class="alert-container ${flash.success?"ab-alert-success":"ab-alert-danger"}" id="alertBox">
                    <div class="alert alert-primary alert-dismissible fade show" role="alert">
                        <strong>${flash.message}</strong>
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close" onclick="document.getElementById('alertBox').remove()">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                </div>
            </g:if>
        </header>
        <g:layoutBody/>
    </div>
</div>
<asset:javascript src="vendor.bundle.base.js" />
<asset:javascript src="Chart.min.js" />
<asset:javascript src="jquery-jvectormap.min.js" />
<asset:javascript src="jquery-jvectormap-world-mill-en.js" />
<asset:javascript src="material.js" />
<asset:javascript src="misc.js" />
<asset:javascript src="dashboard.js" />
</body>
</html>