export default function Home() {
    return null; // This will not be displayed because we are redirecting.
}

export async function getServerSideProps() {
    return {
        redirect: {
            destination: '/reports',
            permanent: false,
        },
    };
}