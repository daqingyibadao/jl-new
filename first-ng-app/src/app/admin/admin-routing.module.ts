import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {AdminComponent} from "./admin.component";
import {EditUserComponent} from "./edit-user/edit-user.component";
import {EditTeacherComponent} from "./edit-teacher/edit-teacher.component";
import {UserListComponent} from "./user-list/user-list.component";
import {ViewUserComponent} from "./view-user/view-user.component";

const routes: Routes = [
  {
    path: '',
    component: AdminComponent
  },
  {
    path: 'user/:id',
    component: EditUserComponent
  },
  {
    path: 'user/modify/:id',
    component: EditUserComponent
  },
  {
    path: 'teacher/:id',
    component: EditTeacherComponent
  },
  {
    path: 'user/:id/view',
    component: ViewUserComponent
  },
  {
    path: 'users',
    component: UserListComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule {
}
