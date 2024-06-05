'use client';

import React, {useState} from 'react';
import {useDropzone} from 'react-dropzone';
import {supabase} from '@/lib/supabaseClient';
import {Button} from "@/components/ui/button";
import {XMarkIcon} from "@heroicons/react/24/solid";

const UploadReportButton: React.FC = () => {
    const [tradeFile, setTradeFile] = useState<File | null>(null);
    const [reportFile, setReportFile] = useState<File | null>(null);
    const [uploading, setUploading] = useState(false);
    const [modalOpen, setModalOpen] = useState(false);

    const onDrop = (acceptedFiles: File[], fileType: 'trade' | 'report') => {
        const file = acceptedFiles[0];
        if (fileType === 'trade') {
            setTradeFile(file);
        } else if (fileType === 'report') {
            setReportFile(file);
        }
    };

    const {getRootProps: getTradeRootProps, getInputProps: getTradeInputProps, open: openTrade} = useDropzone({
        onDrop: (files) => onDrop(files, 'trade'),
        noClick: true,
        noKeyboard: true
    });

    const {getRootProps: getReportRootProps, getInputProps: getReportInputProps, open: openReport} = useDropzone({
        onDrop: (files) => onDrop(files, 'report'),
        noClick: true,
        noKeyboard: true
    });

    const handleButtonClick = () => {
        setModalOpen(true);
    };

    const handleUpload = async () => {
        setUploading(true);
        try {
            if (tradeFile && reportFile) {
                const reportContent = await fileToJson(reportFile);
                const tradeContent = await fileToJson(tradeFile);

                const {data, error} = await supabase
                    .from('trade_reports')
                    .insert([{
                        report: reportContent,
                        trade: tradeContent,
                        is_live: isTradeLive(reportContent),
                        jurisdiction: getJurisdiction(reportContent),
                        effectiveDate: getEffectiveDate(reportContent),
                        expirationDate: getExpirationDate(reportContent),
                    }]);
                if (error) throw error;
                alert('Files uploaded successfully!');
            } else {
                alert('Please upload both trade and report files.');
            }
        } catch (error) {
            console.error('Error uploading files:', error);
            alert('Failed to upload files');
        } finally {
            setUploading(false);
            setTradeFile(null);
            setReportFile(null);
            setModalOpen(false);
        }
    };

    const fileToJson = (file: File): Promise<any> => {
        return new Promise((resolve, reject) => {
            const reader = new FileReader();
            reader.onload = (event: ProgressEvent<FileReader>) => {
                const content = event.target?.result;
                if (typeof content === 'string') {
                    resolve(JSON.parse(content));
                } else {
                    reject('File content is not valid');
                }
            };
            reader.readAsText(file);
        });
    };

    return (
        <div>
            <Button
                className="ml-auto"
                size="sm"
                onClick={handleButtonClick}
                disabled={uploading}
            >
                {uploading ? 'Uploading...' : 'Upload Report'}
            </Button>
            {modalOpen && (
                <div className="fixed inset-0 flex items-center justify-center z-50 bg-gray-900 bg-opacity-75">
                    <div className="bg-black rounded-lg shadow-lg w-11/12 md:w-1/2 lg:w-1/3 p-6 relative">
                        <h2 className="text-2xl font-semibold text-white mb-4">Upload Trade and Report Files</h2>
                        <button
                            className="absolute top-4 right-4 text-gray-400 hover:text-gray-200"
                            onClick={() => setModalOpen(false)}
                        >
                            <XMarkIcon className="h-6 w-6"/>
                        </button>
                        <div className="mb-4">
                            <input {...getTradeInputProps()} style={{display: 'none'}}/>
                            <Button
                                className="w-full bg-gray-800 hover:bg-gray-700 text-white py-2 px-4 rounded"
                                onClick={openTrade}
                                disabled={!!tradeFile}
                            >
                                {tradeFile ? 'Trade File Selected' : 'Upload Trade'}
                            </Button>
                        </div>
                        <div className="mb-4">
                            <input {...getReportInputProps()} style={{display: 'none'}}/>
                            <Button
                                className="w-full bg-gray-800 hover:bg-gray-700 text-white py-2 px-4 rounded"
                                onClick={openReport}
                                disabled={!!reportFile}
                            >
                                {reportFile ? 'Report File Selected' : 'Upload Report'}
                            </Button>
                        </div>
                        <div className="flex justify-end">
                            <Button
                                className="bg-green-500 hover:bg-green-400 text-white py-2 px-4 rounded"
                                onClick={handleUpload}
                                disabled={!tradeFile || !reportFile || uploading}
                            >
                                {uploading ? 'Uploading...' : 'Submit'}
                            </Button>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
};

function isTradeLive(report: {
    expirationDate: string | number | Date;
    effectiveDate: string | number | Date;
}): boolean {
    const expirationDate = new Date(report.expirationDate);
    const effectiveDate = new Date(report.effectiveDate);
    const currentDate = new Date();
    return currentDate < expirationDate && currentDate > effectiveDate;
}

function getJurisdiction(report: { jurisdiction: any; }) {
    return report.jurisdiction;
}

function getEffectiveDate(report: { effectiveDate: any; }) {
    return report.effectiveDate;
}

function getExpirationDate(report: { expirationDate: any; }) {
    return report.expirationDate;
}

export {UploadReportButton};
