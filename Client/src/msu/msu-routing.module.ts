import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {DashboardComponent} from "./dashboard/dashboard.component";
import {ForumComponent} from "./forum/forum.component";
import {AnnouncementsComponent} from "./announcements/announcements.component";
import {ResourcesComponent} from "./resources/resources.component";
import {FaqComponent} from "./faq/faq.component";
import {InstantChatComponent} from "./instant-chat/instant-chat.component";
import {UserProfileComponent} from "./user-profile/user-profile.component";
import {ForumDetailComponent} from "./forum-detail/forum-detail.component";

const routes: Routes = [
  {
    path: '', component: HomeComponent, children: [
      {path: 'dashboard', component: DashboardComponent},
      {path: 'forums', component: ForumComponent},
      {path: 'announcements', component: AnnouncementsComponent},
      {path: 'resources', component: ResourcesComponent},
      {path: 'faq', component: FaqComponent},
      {path: 'instant-chat', component: InstantChatComponent},
      {path: 'profile', component: UserProfileComponent},
    ]
  }, {
    path: 'forum/:id', component: ForumDetailComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MsuRoutingModule {
}
