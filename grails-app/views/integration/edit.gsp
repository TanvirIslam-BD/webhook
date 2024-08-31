<html>
<head>
    <meta name="layout" content="main"/>
    <title></title>
</head>

<body>
<div class="page-wrapper mdc-toolbar-fixed-adjust">
    <main class="content-wrapper">
        <div class="mdc-card">
            <h6>Edit ${integrationProperty.wcInstanceName}</h6>
            <g:form controller="integration" action="update" id="${integrationProperty.id}">
                <div class="mdc-layout-grid">
                    <div class="mdc-layout-grid__inner">
                        <div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-6">
                            <div class="mdc-text-field w-100 mdc-ripple-upgraded" style="--mdc-ripple-fg-size:345px; --mdc-ripple-fg-scale:1.70364; --mdc-ripple-fg-translate-start:16.3px, -141.5px; --mdc-ripple-fg-translate-end:115.5px, -150px;">
                                <input name = "wcInstanceName" required class="mdc-text-field__input" id="text-field-hero-input4" value="${integrationProperty.wcInstanceName}" />
                                <div class="mdc-line-ripple" style="transform-origin: 188px center;"></div>
                                <label for="text-field-hero-input4" class="mdc-floating-label mdc-floating-label--float-above">Instance name</label>
                            </div>
                        </div>
                        <div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-6">
                            <div class="mdc-text-field w-100 mdc-ripple-upgraded" style="--mdc-ripple-fg-size:345px; --mdc-ripple-fg-scale:1.70364; --mdc-ripple-fg-translate-start:16.3px, -141.5px; --mdc-ripple-fg-translate-end:115.5px, -150px;">
                                <input name = "wcInstancePath" required class="mdc-text-field__input" id="text-field-hero-input5" value="${integrationProperty.wcInstancePath}" />
                                <div class="mdc-line-ripple" style="transform-origin: 188px center;"></div>
                                <label for="text-field-hero-input4" class="mdc-floating-label mdc-floating-label--float-above">WebCommander Instance path</label>
                            </div>
                        </div>
                        <div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-6">
                            <div class="mdc-text-field w-100 mdc-ripple-upgraded" style="--mdc-ripple-fg-size:345px; --mdc-ripple-fg-scale:1.70364; --mdc-ripple-fg-translate-start:16.3px, -141.5px; --mdc-ripple-fg-translate-end:115.5px, -150px;">
                                <input name = "externalInstanceId" required class="mdc-text-field__input" id="text-field-hero-input6" value="${integrationProperty.externalInstanceId}" />
                                <div class="mdc-line-ripple" style="transform-origin: 188px center;"></div>
                                <label for="text-field-hero-input4" class="mdc-floating-label mdc-floating-label--float-above">External App Instance Id</label>
                            </div>
                        </div>
                        <div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-12">
                            <div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-12">
                                <g:submitButton name="Update" class="mdc-button mdc-button--raised w-100" />
                            </div>
                        </div>
                        <div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-12">
                            <div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-12">
                                <g:link name="cancel" class="mdc-button mdc-button--raised w-100" controller="integration" action="view">Cancel</g:link>
                            </div>
                        </div>
                    </div>
                </div>
            </g:form>
        </div>
    </main>
</div>

</body>
</html>