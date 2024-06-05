import {supabase} from '@/lib/supabaseClient';

export interface TradeReport {
    id: number;
    report: any;
    trade: any;
    is_live: boolean;
    needs_review: boolean;
    jurisdiction: string;
    effectiveDate: Date;
    expirationDate: Date;
}

export const fetchTradeReports = async (page: number = 1, pageSize: number = 8): Promise<TradeReport[]> => {
    const from = (page - 1) * pageSize;
    const to = from + pageSize - 1;

    const {data, error} = await supabase
        .from('trade_reports')
        .select('*')
        .order('id', {ascending: true})
        .range(from, to)

    if (error) {
        throw new Error(error.message);
    }

    return data || [];
};
