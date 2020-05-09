import Vue from 'vue';
import Router from 'vue-router';
import Home from '@/views/Home';
import Licitacao from '@/views/Licitacao';
import Proposta from '@/views/Proposta';
import Classificacao from '@/views/Classificacao';

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            name: 'Home',
            component: Home
        },
        {
            path: '/licitacoes',
            name: 'licitacoes',
            component: Licitacao
        },
        {
            path: '/propostas',
            name: 'propostas',
            component: Proposta
        },
        {
            path: '/classificar',
            name: 'classificar',
            component: Classificacao
        }
    ]
})