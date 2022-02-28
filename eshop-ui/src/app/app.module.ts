import { NgModule, APP_INITIALIZER, LOCALE_ID } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { WatchListComponent } from './components/watch-list/watch-list.component';
import { HttpClientModule } from '@angular/common/http';
import { AppConfigService } from './providers/app-config.service';

import { registerLocaleData } from '@angular/common';
import localeES from '@angular/common/locales/es';
import { PaymentCreateComponent } from './components/payment-create/payment-create.component';
import { ReactiveFormsModule } from '@angular/forms';
import { PaymentListComponent } from './components/payment-list/payment-list.component';
registerLocaleData(localeES);

export function initConfig(appConfig: AppConfigService) {
  return () => appConfig.loadConfig();
}
@NgModule({
  declarations: [
    AppComponent,
    WatchListComponent,
    PaymentCreateComponent,
    PaymentListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [{
    provide: APP_INITIALIZER,
    useFactory: initConfig,
    deps: [AppConfigService],
    multi: true,
  },
  {
    provide: LOCALE_ID,
    useValue: 'es-ES'
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
