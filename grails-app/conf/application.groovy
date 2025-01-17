// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.djamware.securepage.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.djamware.securepage.UserRole'
grails.plugin.springsecurity.authority.className = 'com.djamware.securepage.Role'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
        [pattern: '/',               access: ['permitAll']],
        [pattern: '/error',          access: ['permitAll']],
        [pattern: '/index',          access: ['permitAll']],
        [pattern: '/index.gsp',      access: ['permitAll']],
        [pattern: '/shutdown',       access: ['permitAll']],
        [pattern: '/assets/**',      access: ['permitAll']],
        [pattern: '/**/js/**',       access: ['permitAll']],
        [pattern: '/**/css/**',      access: ['permitAll']],
        [pattern: '/**/images/**',   access: ['permitAll']],
        [pattern: '/**/favicon.ico', access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
        [pattern: '/assets/**',      filters: 'none'],
        [pattern: '/**/js/**',       filters: 'none'],
        [pattern: '/**/css/**',      filters: 'none'],
        [pattern: '/**/images/**',   filters: 'none'],
        [pattern: '/**/favicon.ico', filters: 'none'],
        [pattern: '/connect/**',        filters: 'none'],
        [pattern: '/notify',            filters: 'none'],
        [pattern: '/remove',            filters: 'none'],
        [pattern: '/is-integrated',     filters: 'none'],
        //Stateless chain
        [
                pattern: '/**',
                filters: 'JOINED_FILTERS'
        ],

        //Traditional, stateful chain
        [
                pattern: '/stateful/**',
                filters: 'JOINED_FILTERS,-restTokenValidationFilter,-restExceptionTranslationFilter'
        ]
]

grails.gorm.default.mapping = {
        version false
}

grails.plugin.springsecurity.logout.postOnly = false