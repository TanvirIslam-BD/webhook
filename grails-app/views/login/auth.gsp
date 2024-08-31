<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="login"/>
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
                            <sec:ifLoggedIn>
                                You are logged in
                            </sec:ifLoggedIn>
                            <g:form  url="${postUrl}" >
                                <div class="mdc-layout-grid">
                                    <div class="mdc-layout-grid__inner">
                                        <div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-12">
                                            <div class="mdc-text-field w-100">
                                                <input class="mdc-text-field__input" id="text-field-hero-input" value="admin" type="text" name="username" />
                                                <div class="mdc-line-ripple"></div>
                                                <label for="text-field-hero-input" class="mdc-floating-label">Username</label>
                                            </div>
                                        </div>
                                        <div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-12">
                                            <div class="mdc-text-field w-100">
                                                <input class="mdc-text-field__input" type="password" name="password" id="text-field-hero-input2" value="London88" />
                                                <div class="mdc-line-ripple"></div>
                                                <label for="text-field-hero-input" class="mdc-floating-label">Password</label>
                                            </div>
                                        </div>
                                        <div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-12">
                                            <g:submitButton name="Login" class="mdc-button mdc-button--raised w-100" />
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