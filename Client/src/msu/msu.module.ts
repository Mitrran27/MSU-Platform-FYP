import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {MsuRoutingModule} from './msu-routing.module';
import {ForumComponent} from "./forum/forum.component";
import {FormsModule} from "@angular/forms";
import {AnnouncementsComponent} from "./announcements/announcements.component";
import {DashboardComponent} from "./dashboard/dashboard.component";
import {FaqComponent} from "./faq/faq.component";
import {HomeComponent} from "./home/home.component";
import {InstantChatComponent} from "./instant-chat/instant-chat.component";
import {ResourcesComponent} from "./resources/resources.component";
import {SidebarComponent} from "./sidebar/sidebar.component";
import {UserProfileComponent} from "./user-profile/user-profile.component";
import {DialogModule} from "primeng/dialog";
import {TableModule} from "primeng/table";
import {InputTextModule} from "primeng/inputtext";
import {ButtonModule} from "primeng/button";
import {MultiSelectModule} from "primeng/multiselect";
import { ForumDetailComponent } from './forum-detail/forum-detail.component';


@NgModule({
  declarations: [ForumComponent,
                 AnnouncementsComponent, DashboardComponent, FaqComponent,
                 HomeComponent, InstantChatComponent, ResourcesComponent, SidebarComponent, UserProfileComponent, ForumDetailComponent],
  imports: [
    CommonModule,
    FormsModule,
    MsuRoutingModule,
    DialogModule,
    TableModule,
    InputTextModule,
    ButtonModule,
    MultiSelectModule
  ]
})
export class MsuModule {
}
