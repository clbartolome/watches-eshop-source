import { NgModule, APP_INITIALIZER } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { WatchListComponent } from './components/watch-list/watch-list.component';
import { HttpClientModule } from '@angular/common/http';
import { AppConfigService } from './providers/app-config.service';

import { ApiService } from './service/api.service';
export function initConfig(appConfig: AppConfigService) {
  return () => appConfig.loadConfig();
}
@NgModule({
  declarations: [
    AppComponent,
    WatchListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [{
    provide: APP_INITIALIZER,
    useFactory: initConfig,
    deps: [AppConfigService],
    multi: true,
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
