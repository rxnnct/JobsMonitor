function getIndex(list, id) {
    for (var i = 0; i < list.length; i++) {
        if (list[i].id === id) {
            return i;
        }
    }
    return -1;
}

//source-get-method-form
//BEGIN --->
var sourceGetMethodApi = Vue.resource('api/source-get-methods{/id}')

Vue.component('source-get-method-form', {
    props: ['sourceGetMethods', 'sourceGetMethodAttr'],
    data: function () {
        return {
            url: '',
            id: '',
            source: ''
        }
    },
    watch: {
        sourceGetMethodAttr: function (newVal) {
            this.url = newVal.url;
            this.id = newVal.id;
            this.source = newVal.source;
        }
    },
    template:
        '<div>' +
        '<input type="text" placeholder="url" v-model="url" />' +
        '<input type="text" placeholder="source (enum number)" v-model="source" />' +
        '<input type="button" value="Save" @click="save" />' +
        '</div>',
    methods: {
        save: function () {
            var sourceGetMethod = {url: this.url, source: this.source};

            if (this.id) {
                sourceGetMethodApi.update({id: this.id}, sourceGetMethod).then(result =>
                    result.json().then(data => {
                        var index = getIndex(this.sourceGetMethods, data.id);
                        this.sourceGetMethods.splice(index, 1, data);
                        this.url = '';
                        this.id = '';
                        this.source = ''
                    })
                )
            } else {
                sourceGetMethodApi.save({}, sourceGetMethod).then(result =>
                    result.json().then(data => {
                        this.sourceGetMethods.push(data);
                        this.url = '';
                        this.source = ''
                    })
                )
            }
        }
    }
});

Vue.component('source-get-method-row', {
    props: ['sourceGetMethod', 'editMethod', 'sourceGetMethods'],
    template: '<div>' +
        '<b> id: {{ sourceGetMethod.id }} </b>url: {{ sourceGetMethod.url }} <i>Source: {{ sourceGetMethod.source }}</i>' +
        '<span style="position: absolute; right: 0">' +
        '<input type="button" value="Edit" @click="edit" />' +
        '<input type="button" value="Delete" @click="del" />' +
        '</span>' +
        '</div>',
    methods: {
        edit: function () {
            this.editMethod(this.sourceGetMethod);
        },
        del: function () {
            sourceGetMethodApi.remove({id: this.sourceGetMethod.id}).then(result => {
                if (result.ok) {
                    this.sourceGetMethods.splice(this.sourceGetMethods.indexOf(this.sourceGetMethod), 1)
                }
            })
        }
    }
});

Vue.component('source-get-methods-list', {
    props: ['sourceGetMethods'],
    data: function () {
        return {
            sourceGetMethod: null
        }
    },
    template:
        '<div style="position: relative; width: 600px; outline: 1px solid; padding: 3px; margin: 3px">' +
        '<source-get-method-form :sourceGetMethods="sourceGetMethods" :sourceGetMethodAttr="sourceGetMethod" />' +
        '<source-get-method-row v-for="(sourceGetMethod, id) in sourceGetMethods" v-bind:key="sourceGetMethod.id" :sourceGetMethod="sourceGetMethod" ' +
        ':editMethod="editMethod" :sourceGetMethods="sourceGetMethods" />' +
        '</div>',
    created: function () {
        sourceGetMethodApi.get().then(result =>
            result.json().then(data =>
                data.forEach(sourceGetMethod => this.sourceGetMethods.push(sourceGetMethod))
            )
        )
    },
    methods: {
        editMethod: function (sourceGetMethod) {
            this.sourceGetMethod = sourceGetMethod;
        }
    }
});

var sourceGetMethodApp = new Vue({
    el: '#sourceGetMethodApp',
    template: '<source-get-methods-list :sourceGetMethods="sourceGetMethods" />',
    data: {
        sourceGetMethods: []
    }
});
//<--- END

//proxy-property-form
//BEGIN --->
var proxyPropertyApi = Vue.resource('api/proxy-properties{/id}')

Vue.component('proxy-property-form', {
    props: ['proxyProperties', 'proxyPropertyAttr'],
    data: function () {
        return {
            ip: '',
            id: '',
            port: '',
            active: ''

        }
    },
    watch: {
        proxyPropertyAttr: function (newVal) {
            this.ip = newVal.ip;
            this.id = newVal.id;
            this.port = newVal.port;
            this.active = newVal.active;
        }
    },
    template:
        '<div>' +
        '<input type="text" placeholder="ip" v-model="ip" />' +
        '<input type="text" placeholder="port" v-model="port" />' +
        '<input type="text" placeholder="active (boolean)" v-model="active" />' +
        '<input type="button" value="Save" @click="save" />' +
        '</div>',
    methods: {
        save: function () {
            var proxyProperty = {ip: this.ip, port: this.port, active: this.active};

            if (this.id) {
                proxyPropertyApi.update({id: this.id}, proxyProperty).then(result =>
                    result.json().then(data => {
                        var index = getIndex(this.proxyProperties, data.id);
                        this.proxyProperties.splice(index, 1, data);
                        this.ip = '';
                        this.id = '';
                        this.port = '';
                        this.active = ''
                    })
                )
            } else {
                proxyPropertyApi.save({}, proxyProperty).then(result =>
                    result.json().then(data => {
                        this.proxyProperties.push(data);
                        this.ip = '';
                        this.port = '';
                        this.active = ''
                    })
                )
            }
        }
    }
});

Vue.component('proxy-property-row', {
    props: ['proxyProperty', 'editMethod', 'proxyProperties'],
    template: '<div>' +
        '<b> id: {{ proxyProperty.id }} </b>ip: {{ proxyProperty.ip }} <i>port: {{ proxyProperty.port }}</i> <u>active: {{ proxyProperty.active }}</u>' +
        '<span style="position: absolute; right: 0">' +
        '<input type="button" value="Edit" @click="edit" />' +
        '<input type="button" value="Delete" @click="del" />' +
        '</span>' +
        '</div>',
    methods: {
        edit: function () {
            this.editMethod(this.proxyProperty);
        },
        del: function () {
            proxyPropertyApi.remove({id: this.proxyProperty.id}).then(result => {
                if (result.ok) {
                    this.proxyProperties.splice(this.proxyProperties.indexOf(this.proxyProperty), 1)
                }
            })
        }
    }
});

Vue.component('proxy-properties-list', {
    props: ['proxyProperties'],
    data: function () {
        return {
            proxyProperty: null
        }
    },
    template:
        '<div style="position: relative; width: 600px; outline: 1px solid; padding: 3px; margin: 3px">' +
        '<proxy-property-form :proxyProperties="proxyProperties" :proxyPropertyAttr="proxyProperty" />' +
        '<proxy-property-row v-for="(proxyProperty, id) in proxyProperties" v-bind:key="proxyProperty.id" :proxyProperty="proxyProperty" ' +
        ':editMethod="editMethod" :proxyProperties="proxyProperties" />' +
        '</div>',
    created: function () {
        proxyPropertyApi.get().then(result =>
            result.json().then(data =>
                data.forEach(proxyProperty => this.proxyProperties.push(proxyProperty))
            )
        )
    },
    methods: {
        editMethod: function (proxyProperty) {
            this.proxyProperty = proxyProperty;
        }
    }
});

var proxyPropertyApp = new Vue({
    el: '#proxyPropertyApp',
    template: '<proxy-properties-list :proxyProperties="proxyProperties" />',
    data: {
        proxyProperties: []
    }
});
//<--- END

//scheduler-cron-expression-form
//BEGIN --->
var schedulerCronExpressionApi = Vue.resource('api/scheduler-cron-expressions{/id}')

Vue.component('scheduler-cron-expression-form', {
    props: ['schedulerCronExpressions', 'schedulerCronExpressionAttr'],
    data: function () {
        return {
            expression: '',
            id: '',
            source: ''
        }
    },
    watch: {
        schedulerCronExpressionAttr: function (newVal) {
            this.expression = newVal.expression;
            this.id = newVal.id;
            this.source = newVal.source;
        }
    },
    template:
        '<div>' +
        '<input type="text" placeholder="expression" v-model="expression" />' +
        '<input type="text" placeholder="source (enum number)" v-model="source" />' +
        '<input type="button" value="Save" @click="save" />' +
        '</div>',
    methods: {
        save: function () {
            var schedulerCronExpression = {expression: this.expression, source: this.source};

            if (this.id) {
                schedulerCronExpressionApi.update({id: this.id}, schedulerCronExpression).then(result =>
                    result.json().then(data => {
                        var index = getIndex(this.schedulerCronExpressions, data.id);
                        this.schedulerCronExpressions.splice(index, 1, data);
                        this.expression = '';
                        this.id = '';
                        this.source = ''
                    })
                )
            } else {
                schedulerCronExpressionApi.save({}, schedulerCronExpression).then(result =>
                    result.json().then(data => {
                        this.schedulerCronExpressions.push(data);
                        this.expression = '';
                        this.source = ''
                    })
                )
            }
        }
    }
});

Vue.component('scheduler-cron-expression-row', {
    props: ['schedulerCronExpression', 'editMethod', 'schedulerCronExpressions'],
    template: '<div>' +
        '<b> id: {{ schedulerCronExpression.id }} </b>expression: {{ schedulerCronExpression.expression }} <i>Source: {{ schedulerCronExpression.source }}</i>' +
        '<span style="position: absolute; right: 0">' +
        '<input type="button" value="Edit" @click="edit" />' +
        '<input type="button" value="Delete" @click="del" />' +
        '</span>' +
        '</div>',
    methods: {
        edit: function () {
            this.editMethod(this.schedulerCronExpression);
        },
        del: function () {
            schedulerCronExpressionApi.remove({id: this.schedulerCronExpression.id}).then(result => {
                if (result.ok) {
                    this.schedulerCronExpressions.splice(this.schedulerCronExpressions.indexOf(this.schedulerCronExpression), 1)
                }
            })
        }
    }
});

Vue.component('scheduler-cron-expressions-list', {
    props: ['schedulerCronExpressions'],
    data: function () {
        return {
            schedulerCronExpression: null
        }
    },
    template:
        '<div style="position: relative; width: 600px; outline: 1px solid; padding: 3px; margin: 3px">' +
        '<scheduler-cron-expression-form :schedulerCronExpressions="schedulerCronExpressions" :schedulerCronExpressionAttr="schedulerCronExpression" />' +
        '<scheduler-cron-expression-row v-for="(schedulerCronExpression, id) in schedulerCronExpressions" v-bind:key="schedulerCronExpression.id" :schedulerCronExpression="schedulerCronExpression" ' +
        ':editMethod="editMethod" :schedulerCronExpressions="schedulerCronExpressions" />' +
        '</div>',
    created: function () {
        schedulerCronExpressionApi.get().then(result =>
            result.json().then(data =>
                data.forEach(schedulerCronExpression => this.schedulerCronExpressions.push(schedulerCronExpression))
            )
        )
    },
    methods: {
        editMethod: function (schedulerCronExpression) {
            this.schedulerCronExpression = schedulerCronExpression;
        }
    }
});

var schedulerCronExpressionApp = new Vue({
    el: '#schedulerCronExpressionApp',
    template: '<scheduler-cron-expressions-list :schedulerCronExpressions="schedulerCronExpressions" />',
    data: {
        schedulerCronExpressions: []
    }
});
//<--- END