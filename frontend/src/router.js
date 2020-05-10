import Vue from 'vue';
import Router from 'vue-router';
import Licitacao from '@/views/Licitacao';
import Proposta from '@/views/Proposta';

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/licitacoes',
            name: 'licitacoes',
            component: Licitacao
        },
        {
            path: '/propostas',
            name: 'propostas',
            component: Proposta,
            props: true
        }
    ]
})