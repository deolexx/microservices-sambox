import {Elements} from '@stripe/react-stripe-js';
import {loadStripe} from '@stripe/stripe-js';

import CheckoutForm from './CheckoutForm';
import React from "react";


const stripePromise = loadStripe("pk_test_51KlygqJyIM88GoIrV0ctH5CB3MV3iYAdBw9cPqdY1kjzbDzsWXaQ7iknOfrRgTeokZqZgldOsO8v85KhIJwH5vYJ00eBrW52sP");

export function StripeForm({client_secret}) {
    return (

        <div>
            <Elements stripe={stripePromise}>
                <CheckoutForm client_secret={client_secret}/>
            </Elements>
        </div>

    );
}
