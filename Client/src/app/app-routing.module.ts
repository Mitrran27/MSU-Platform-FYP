import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthenticationComponent} from "./authentication/authentication.component";
import {ChatBotComponent} from "./chatbot/chat-bot.component";

const routes: Routes = [
  {
    path: '', loadChildren: () => import('../msu/msu.module').then(m => m.MsuModule)
  },
  {
    path: 'authentication', component: AuthenticationComponent
  },
  {path: 'chat-bot', component: ChatBotComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
