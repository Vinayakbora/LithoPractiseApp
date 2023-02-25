package com.example.lithoapp.ui

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import androidx.appcompat.app.AppCompatActivity
import com.example.lithoapp.domain.PhonePreference
import com.example.lithoapp.R
import com.example.lithoapp.domain.Validations
import com.facebook.litho.*
import com.facebook.litho.core.margin
import com.facebook.litho.kotlin.widget.Image
import com.facebook.litho.kotlin.widget.Text
import com.facebook.litho.kotlin.widget.TextInput
import com.facebook.litho.view.backgroundColor
import com.facebook.litho.view.onClick
import com.facebook.soloader.SoLoader


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SoLoader.init(this, false)

        val lithoView = LithoView.create(this, MyComponent(this))
        setContentView(lithoView)

    }
}

class MyComponent(ctx: Context) : KComponent() {
    private val mobilePref = PhonePreference(ctx)
    private var number = mobilePref.getData()
    private val validation = Validations(ctx)
    private var isLoggedIn = mobilePref.getLoginStatus()

    override fun ComponentScope.render(): Component {
        return Column(
            style = Style.backgroundColor(
                context.resources.getColor(
                    R.color.primaryContainer, null
                )
            )
        ) {
            child(Row {
                child(
                    Image(
                        drawable = drawableRes(R.drawable.bajaj_markets_light_blue_1),
                        style = Style.margin(top = 40.dp, start = 29.6.dp, end = 216.9.dp)
                    ),
                )
            })
            child(Row {
                child(
                    Text(
                        text = "Let’s get started!",
                        style = Style.margin(top = 50.dp, start = 30.dp, end = 187.dp),
                        textSize = 21.sp,
                        textColor = Color.WHITE
                    )
                )
            })
            child(Row {
                child(
                    Text(
                        text = "You give us the digits, we’ll send you all the existing \n" + "rewards & offers!",
                        style = Style.margin(top = 10.dp, start = 30.dp, end = 79.dp),
                        textSize = 12.sp,
                        textColor = context.resources.getColor(R.color.greyish, null)
                    )
                )
            })
            child(Row {
                child(
                    TextInput(inputType = InputType.TYPE_CLASS_PHONE,
                        initialText = if (isLoggedIn) number else "",
                        hint = "Mobile Number",
                        style = Style.margin(top = 50.dp, start = 30.dp, end = 33.5.dp),
                        textSize = 21.sp,
                        multiline = false,
                        textColor = context.resources.getColor(R.color.steel, null),
                        hintTextColor = context.resources.getColor(R.color.steel, null),
                        onTextChanged = {
                            number = it.text
                        })
                )
            })
            child(Row {
                child(
                    Text(
                        text = "I agree to",
                        style = Style.margin(top = 50.5.dp, start = 30.dp),
                        textSize = 12.sp,
                        textColor = context.resources.getColor(R.color.greyish, null)
                    )
                )
                child(
                    Text(
                        text = " Terms & Conditions",
                        style = Style.margin(top = 50.5.dp, end = 192.dp),
                        textSize = 12.sp,
                        textColor = context.resources.getColor(R.color.primary, null)
                    )
                )

            })
            child(Row {
                child(
                    Text(
                        text = "I give consent for",
                        style = Style.margin(top = 17.dp, start = 30.dp),
                        textSize = 12.sp,
                        textColor = context.resources.getColor(R.color.greyish, null)
                    )
                )
                child(
                    Image(
                        drawable = drawableRes(R.drawable.whatsapp_1),
                        style = Style.margin(top = 17.dp, start = 5.dp)
                    ),
                )
                child(
                    Text(
                        text = " WhatsApp updates",
                        style = Style.margin(top = 17.dp, start = 5.dp),
                        textSize = 12.sp,
                        textColor = context.resources.getColor(R.color.greyish, null)
                    )
                )
            })
            child(Row {
                child(
                    Image(drawable = drawableRes(R.drawable.group_2287),
                        style = Style.margin(top = 153.dp, start = 303.dp, end = 30.dp).onClick {
                            validation.validatePhone(number)
                        })
                )
            })
        }
    }
}