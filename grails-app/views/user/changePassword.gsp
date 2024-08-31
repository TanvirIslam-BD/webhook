<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title></title>
</head>
<body>

    <div class="page-wrapper full-page-wrapper d-flex align-items-center justify-content-center">
        <main class="auth-page">
            <div class="mdc-layout-grid">
                <div class="mdc-layout-grid__inner">
                    <div class="stretch-card mdc-layout-grid__cell--span-4-desktop mdc-layout-grid__cell--span-1-tablet"></div>
                    <div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-4-desktop mdc-layout-grid__cell--span-6-tablet">
                        <div class="mdc-card">
                            <g:form action="doChangePassword" >
                                <div class="mdc-layout-grid">
                                    <div class="mdc-layout-grid__inner">
                                        <div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-12">
                                            <div class="mdc-text-field w-100">
                                                <input class="mdc-text-field__input" type="password" name="oldPassword" id="text-field-hero-input3" value="Hilo89#3" />
                                                <div class="mdc-line-ripple"></div>
                                                <label for="text-field-hero-input3" class="mdc-floating-label">Old Password</label>
                                            </div>
                                        </div>
                                        <div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-12">
                                            <div class="mdc-text-field w-100">
                                                <input class="mdc-text-field__input" type="password" name="newPassword" id="text-field-hero-input2" value="Hilo89#3" />
                                                <div class="mdc-line-ripple"></div>
                                                <label for="text-field-hero-input2" class="mdc-floating-label">New Password</label>
                                            </div>
                                        </div>
                                        <div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-12">
                                            <g:submitButton name="Change Password" class="mdc-button mdc-button--raised w-100" />
                                        </div>
                                    </div>
                                </div>
                            </g:form>
                        </div>
                    </div>
                    <div class="stretch-card mdc-layout-grid__cell--span-4-desktop mdc-layout-grid__cell--span-1-tablet"></div>
                </div>
            </div>
        </main>
    </div>

</body>
</html>