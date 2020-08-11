function getIndex(list, id) {
    for (var i = 0; i < list.length; i++) {
        if(list[i].id === id){
            return i;
        }
    }
    return -1;
}

var sourceGetMethodApi = Vue.resource('api/source-get-methods{/id}')

Vue.component('source-get-method-form', {
    props: ['sourceGetMethods', 'sourceGetMethodAttr'],
    data: function () {
        return{
            url: '',
            id: '',
            source: ''
        }
    },
    watch: {
        sourceGetMethodAttr: function(newVal, oldVal){
            this.url = newVal.url;
            this.id = newVal.id;
            this.source = newVal.source;
        }
    },
    template:
        '<div>' +
        '<input type="text" placeholder="Url" v-model="url" />' +
        '<input type="text" placeholder="Source" v-model="source" />' +
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
                this.name = '';
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
                if (result.ok){
                this.sourceGetMethods.splice(this.sourceGetMethods.indexOf(this.sourceGetMethod), 1)
            }
        })
        }
    }
});

Vue.component('source-get-methods-list', {
    props: ['sourceGetMethods'],
    data: function(){
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

var adminApp = new Vue({
    el: '#adminApp',
    template: '<source-get-methods-list :sourceGetMethods="sourceGetMethods" />',
    data: {
        sourceGetMethods: [
        ]
    }
});