'use client';
import {useEffect, useState} from "react";
import {Table} from "@/components/ui/table";
import {UploadReportButton} from "@/components/UploadReportButton";
import {fetchTradeReports, TradeReport} from "@/utils/fetchTradeReports";
import {Button} from "@/components/ui/button"; // Import the Button component

export function Reports() {
    const [reports, setReports] = useState<TradeReport[]>([]);
    const [page, setPage] = useState<number>(1);
    const [loading, setLoading] = useState<boolean>(true);
    const pageSize = 8;

    useEffect(() => {
        const loadReports = async () => {
            setLoading(true);
            try {
                const data = await fetchTradeReports(page, pageSize);
                setReports(data);
            } catch (error) {
                console.error('Error fetching trade reports:', error);
            } finally {
                setLoading(false);
            }
        };

        loadReports();
    }, [page]);

    const handleNextPage = () => {
        setPage((prevPage) => prevPage + 1);
    };

    const handlePrevPage = () => {
        setPage((prevPage) => Math.max(prevPage - 1, 1));
    };

    return (
        <div className="grid min-h-screen w-full">
            <div className="flex flex-col w-full">
                <main className="flex flex-1 flex-col gap-4 p-4 md:gap-8 md:p-6">
                    <div className="flex items-center">
                        <h1 className="font-semibold text-lg md:text-2xl">Trade Reports</h1>
                        <div className="flex justify-end w-full">
                            <UploadReportButton/>
                        </div>
                    </div>
                    <div>
                        {loading ? (
                            <p>Loading...</p>
                        ) : (
                            <Table data={reports}/>
                        )}
                        <div className="flex justify-between items-center mt-4">
                            <Button
                                className="px-4 py-2"
                                onClick={handlePrevPage}
                                disabled={page === 1}
                                variant="default" // Ensure the same variant as Upload Report Button
                            >
                                Previous
                            </Button>
                            <span>Page {page}</span>
                            <Button
                                className="px-4 py-2"
                                onClick={handleNextPage}
                                variant="default" // Ensure the same variant as Upload Report Button
                            >
                                Next
                            </Button>
                        </div>
                    </div>
                </main>
            </div>
        </div>
    );
}
