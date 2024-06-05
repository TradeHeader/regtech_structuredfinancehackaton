import {NextApiRequest, NextApiResponse} from 'next';
import {createClient} from '@supabase/supabase-js';

const supabaseUrl = process.env.NEXT_PUBLIC_SUPABASE_URL!;
const supabaseAnonKey = process.env.NEXT_PUBLIC_SUPABASE_ANON_KEY!;
const supabase = createClient(supabaseUrl, supabaseAnonKey);

type Report = {
    jurisdiction: string;
    created_at: string;
    updated_at: string;
    valid_from: string;
    valid_to?: string;
    [key: string]: any; // Allow for other properties
};

export default async function handler(req: NextApiRequest, res: NextApiResponse) {
    if (req.method === 'POST') {
        const {report} = req.body as { report: Report };

        if (!report || typeof report !== 'object') {
            return res.status(400).json({error: 'Invalid report format'});
        }

        const currentDate = new Date();
        const isLive = currentDate >= new Date(report.valid_from) && (!report.valid_to || currentDate <= new Date(report.valid_to));

        const {data, error} = await supabase
            .from('trade_reports')
            .insert([
                {
                    report,
                    is_live: isLive,
                    needs_review: false
                }
            ]);

        if (error) {
            return res.status(500).json({error: error.message});
        }

        return res.status(200).json({message: 'Report uploaded successfully', data});
    } else {
        res.setHeader('Allow', ['POST']);
        res.status(405).end(`Method ${req.method} Not Allowed`);
    }
}
