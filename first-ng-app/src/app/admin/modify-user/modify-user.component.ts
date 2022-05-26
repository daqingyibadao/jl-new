import {Component, OnInit} from '@angular/core';
import {ModifyUserService} from "./modify-user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-view-user',
  templateUrl: './modify-user.component.html',
  styleUrls: ['./modify-user.component.css']
})
export class ModifyUserComponent implements OnInit {

  constructor(private readonly _modifyUserService: ModifyUserService,
              private readonly _router: Router) {
  }

  ngOnInit(): void {
    this._modifyUserService.getUserData().subscribe(user => {
      // display to user
      console.log(user);
    });
  }

  backToHome() {
    this._router.navigate(['/admin/users']);
  }

}
