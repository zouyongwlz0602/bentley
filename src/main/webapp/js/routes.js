'use strict';

define(['app'], function(app) {
    return app.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise('/home');
        $stateProvider
            .state('home', {
                url: '/home',
                views: {
                    '@': {
                        templateUrl: '/pages/home/home.html'
                    }
                },
                ncyBreadcrumb: {
                    label: '首页'
                }
            })
            .state('news', {
                url: '/news',
                views: {
                    'left-menu@news': {
                        templateUrl: '/pages/menu/news-menu.html'
                    },
                    'content@news': {
                        templateUrl: '/pages/news/ship-company.html',
                        controller: 'ShipCompanyCtrl',
                        controllerAs: 'vm'
                    },
                    '@': {
                        templateUrl: '/pages/templates/content-main.html'
                    }
                },
                ncyBreadcrumb: {
                    label: "最新资讯"
                }
            })
            .state('news.ship', {
                url: '/ship',
                views: {
                    'content@news': {
                        templateUrl: '/pages/news/ship-company.html',
                        controller: 'ShipCompanyCtrl'
                    }
                },
                ncyBreadcrumb: {
                    label: '俱乐部简介'
                }
            })
            .state('news.dynamic', {
                url: '/dynamic',
                views: {
                    'content@news': {
                        templateUrl: '/pages/news/club-dynamic-list.html',
                        controller: 'ClubDynamicCtrl',
                        controllerAs: 'vm'
                    }
                },
                ncyBreadcrumb: {
                    label: '俱乐部动态'
                }
            })
            .state('news.dynamic.form', {
                url: '/dynamicForm',
                views: {
                    'content@news': {
                        templateUrl: '/pages/news/club-dynamic-form.html',
                        controller: 'ClubDynamicFormCtrl',
                        controllerAs: 'vm'
                    }
                },
                ncyBreadcrumb: {
                    label: '俱乐部动态创建'
                }
            })
            .state('news.dynamic.edit', {
                url: '/dynamicEdit/:id',
                views: {
                    'content@news': {
                        templateUrl: '/pages/news/club-dynamic-edit.html',
                        controller: 'ClubDynamicEditCtrl',
                        controllerAs: 'vm'
                    }
                },
                ncyBreadcrumb: {
                    label: '俱乐部动态编辑'
                }
            })
            .state('news.activity', {
                url: '/activity',
                views: {
                    'content@news': {
                        templateUrl: '/pages/news/club-activity-list.html',
                        controller: 'ClubActivityCtrl',
                        controllerAs: 'vm'
                    }
                },
                ncyBreadcrumb: {
                    label: '会员活动回顾'
                }
            })
            .state('news.activity.form', {
                url: '/activityForm',
                views: {
                    'content@news': {
                        templateUrl: '/pages/news/club-activity-form.html',
                        controller: 'ClubActivityFormCtrl',
                        controllerAs: 'vm'
                    }
                },
                ncyBreadcrumb: {
                    label: '会员活动创建'
                }
            })
            .state('news.activity.edit', {
                url: '/activityEdit/:id',
                views: {
                    'content@news': {
                        templateUrl: '/pages/news/club-activity-edit.html',
                        controller: 'ClubActivityEditCtrl',
                        controllerAs: 'vm'
                    }
                },
                ncyBreadcrumb: {
                    label: '会员活动编辑'
                }
            })
            .state('destine', {
                url: '/destine',
                views: {
                    'left-menu@destine': {
                        templateUrl: '/pages/menu/destine-menu.html'
                    },
                    'content@destine': {
                        templateUrl: '/pages/destine/hotel-destine-list.html',
                        controller: 'HotelDestineCtrl',
                        controllerAs: 'vm'
                    },
                    '@': {
                        templateUrl: '/pages/templates/content-main.html'
                    }
                },
                ncyBreadcrumb: {
                    label: "预订服务"
                }
            })
            .state('destine.hotel', {
                url: '/destineHotel',
                views: {
                    'content@destine': {
                        templateUrl: '/pages/destine/hotel-destine-list.html',
                        controller: 'HotelDestineCtrl',
                        controllerAs: 'vm'
                    }
                },
                ncyBreadcrumb: {
                    label: "酒店预订"
                }
            })
            .state('destine.yacht', {
                url: '/destineYacht',
                views: {
                    'content@destine': {
                        templateUrl: '/pages/destine/yacht-destine-list.html',
                        controller: 'YachtDestineCtrl',
                        controllerAs: 'vm'
                    }
                },
                ncyBreadcrumb: {
                    label: "游艇预订"
                }
            })
            .state('destine.activity', {
                url: '/destineActivity',
                views: {
                    'content@destine': {
                        templateUrl: '/pages/destine/activity-destine-list.html',
                        controller: 'ActivityDestineCtrl',
                        controllerAs: 'vm'
                    }
                },
                ncyBreadcrumb: {
                    label: "私人活动预订"
                }
            })
            .state('memberCenter', {
                url: '/memberCenter',
                views: {
                    'left-menu@memberCenter': {
                        templateUrl: '/pages/menu/member-center-menu.html'
                    },
                    'content@memberCenter': {
                        templateUrl: '/pages/member/washcar-list.html',
                        controller: 'WashCarCtrl',
                        controllerAs: 'vm'
                    },
                    '@': {
                        templateUrl: '/pages/templates/content-main.html'
                    }
                },
                ncyBreadcrumb: {
                    label: "会员中心"
                }
            })
            .state('memberCenter.washcar', {
                url: '/memberCenterWashcar',
                views: {
                    'content@memberCenter': {
                        templateUrl: '/pages/member/washcar-list.html',
                        controller: 'WashCarCtrl',
                        controllerAs: 'vm'
                    }
                },
                ncyBreadcrumb: {
                    label: "上门洗车"
                }
            })
            .state('memberCenter.waxing', {
                url: '/memberCenterWaxing',
                views: {
                    'content@memberCenter': {
                        templateUrl: '/pages/member/waxing-list.html',
                        controller: 'WaxingCtrl',
                        controllerAs: 'vm'
                    }
                },
                ncyBreadcrumb: {
                    label: "上门打蜡"
                }
            })
            .state('memberCenter.maintenance', {
                url: '/memberCenterMaintenance',
                views: {
                    'content@memberCenter': {
                        templateUrl: '/pages/member/maintenance-list.html',
                        controller: 'MaintenanceCtrl',
                        controllerAs: 'vm'
                    }
                },
                ncyBreadcrumb: {
                    label: "预约保养"
                }
            })
            .state('memberCenter.rescue', {
                url: '/memberCenterRescue',
                views: {
                    'content@memberCenter': {
                        templateUrl: '/pages/member/rescue-list.html',
                        controller: 'RescueCtrl',
                        controllerAs: 'vm'
                    }
                },
                ncyBreadcrumb: {
                    label: "市内救援"
                }
            })
            .state('manager', {
                url: '/manager',
                views: {
                    'left-menu@manager': {
                        templateUrl: '/pages/menu/manager-menu.html'
                    },
                    'content@manager': {
                        templateUrl: '/pages/member/member-manager-list.html',
                        controller: 'MemberManagerCtrl',
                        controllerAs: 'vm'
                    },
                    '@': {
                        templateUrl: '/pages/templates/content-main.html'
                    }
                },
                ncyBreadcrumb: {
                    label: "设置"
                }
            })
            .state('manager.member', {
                url: '/managerMember',
                views: {
                    'content@manager': {
                        templateUrl: '/pages/member/member-manager-list.html',
                        controller: 'MemberManagerCtrl',
                        controllerAs: 'vm'
                    }
                },
                ncyBreadcrumb: {
                    label: "用户设置"
                }
            })
            .state('manager.member.edit', {
                url: '/managerMemberEdit/:id',
                views: {
                    'content@manager': {
                        templateUrl: '/pages/member/member-edit.html',
                        controller: 'MemberEditCtrl',
                        controllerAs: 'vm'
                    }
                },
                ncyBreadcrumb: {
                    label: "用户设置"
                }
            })
            .state('manager.group', {
                url: '/group',
                views: {
                    'content@manager': {
                        templateUrl: '/pages/member/weixin-bulk.html',
                        controller: 'GroupCtrl',
                        controllerAs: 'vm'
                    }
                },
                ncyBreadcrumb: {
                    label: "微信群发"
                }
            });

    }]);
});
