import CreatePayment from './CreatePayment'
import SimpleWindow from "./SimpleWindow";
import {useState} from "react";
import {StripeForm} from "./StripeForm";


export default function StripeMainPage() {

    const [clientSecret, setClientSecret] = useState('');

    const receiveClientSecret = (clientSecret) => {
        setClientSecret(clientSecret);
    }

    return (
        <div className="Demo">
            <div className="DemoPickerWrapper">
                <SimpleWindow client_secret={clientSecret}/>
            </div>
            <div className="TextBox">
                <CreatePayment childToParent={receiveClientSecret}/>
            </div>
            <div className="TextBox">
                <StripeForm client_secret={clientSecret}/>
            </div>
        </div>
    )

}


