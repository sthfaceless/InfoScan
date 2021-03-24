import {getCookie} from 'js/services/cookie-service';
import {authWithToken} from "./actions/authActions";

export function getInitialState() {
    return {
        auth: auth(),
        support: support(),
        profile: profile(),
        coins: coins(),
        orders: orders(),
        modal: modal()
    }
}

const auth = () => {
    const action = authWithToken(getCookie('auth_token'));
    return {
        isAuth: action.code === 0,
        username: action.state.username,
        token: action.state.token
    };
};

const support = () => ({
   groups: [{
       name: 'Аккаунт',
       questions: [
           {
               id: '#forgot_password',
               question: 'Забыли пароль?',
               answer: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusamus accusantium atque distinctio dolor dolorum et id illum, impedit neque nesciunt, nostrum numquam porro recusandae repellat similique tempora vel voluptas voluptates?'
           },{
               id: '#forgot_email',
               question: 'Забыли емайл?',
               answer: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusamus accusantium atque distinctio dolor dolorum et id illum, impedit neque nesciunt, nostrum numquam porro recusandae repellat similique tempora vel voluptas voluptates?'
           }
       ]
   },{
       name: 'Коины',
       questions: [
           {
               id: '#whats_coins',
               question: 'Что такое коины?',
               answer: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusamus accusantium atque distinctio dolor dolorum et id illum, impedit neque nesciunt, nostrum numquam porro recusandae repellat similique tempora vel voluptas voluptates?'
           },{
               id: '#buy_coins',
               question: 'Как купить коины?',
               answer: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusamus accusantium atque distinctio dolor dolorum et id illum, impedit neque nesciunt, nostrum numquam porro recusandae repellat similique tempora vel voluptas voluptates?'
           }
       ]
   },{
       name: 'Заказы',
       questions: [
           {
               id: '#make_order',
               question: 'Как сделать заказ?',
               answer: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusamus accusantium atque distinctio dolor dolorum et id illum, impedit neque nesciunt, nostrum numquam porro recusandae repellat similique tempora vel voluptas voluptates?'
           },{
               id: '#longtime_order',
               question: 'Слишком долго выполняется заказ?',
               answer: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusamus accusantium atque distinctio dolor dolorum et id illum, impedit neque nesciunt, nostrum numquam porro recusandae repellat similique tempora vel voluptas voluptates?'
           }
       ]
   }
   ]
});

const profile = () => ({
   info: {
       infoCards: []
   }
});

const coins = () => ({
    searchFilters: [{
        name: 'Дата',
        elements: ['Новые', 'Старые']
    }],
    balance: undefined,
    payments: {
        items: [],
        more: false
    }
});

const orders = () => ({
    searchFilters: [{
        name: 'Дата',
        elements: ['Новые', 'Старые']
    },{
        name: 'Статус',
        elements: ['Принят', 'В обработке', 'Отклонён', 'Завершён']
    }],
    ordersStat: {
        'accepted': undefined,
        'processing': undefined,
        'cancelled': undefined,
        'finished': undefined
    },
    items: {},
    more: false
});

const modal = () => ({
    isOpen: false
});