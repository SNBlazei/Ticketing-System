import { config } from './app.config.server';
import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ConfigurationFormComponent } from './configuration-form/configuration-form.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule,ConfigurationFormComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'ticketing-system';

  configureSystem(config:any){
    console.log("Configuration done:" + config)

  }
}
